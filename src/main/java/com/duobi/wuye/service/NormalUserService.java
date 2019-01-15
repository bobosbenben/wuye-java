package com.duobi.wuye.service;

import com.duobi.wuye.dao.NormalUserDao;
import com.duobi.wuye.dto.NormalUserAddressDTO;
import com.duobi.wuye.entity.NormalUserEntity;
import com.duobi.wuye.entity.addressEntity.BaseAddressEntity;
import com.duobi.wuye.entity.addressEntity.NormalUserAddressEntity;
import com.duobi.wuye.entity.utilEntity.LabelValueTreeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NormalUserService {

    @Autowired
    private NormalUserDao normalUserDao;

    public NormalUserAddressDTO getUsersDefaultAddressByNormalUserId(NormalUserEntity normalUserEntity){
        if (normalUserEntity == null) return null;
        NormalUserAddressEntity n =  normalUserDao.getUsersDefaultAddressByNormalUserId(normalUserEntity.getId());
        if (n == null) return null;

        return copyNormalUserAddressEntityToDTO(n);
    }

    public NormalUserAddressDTO getUserDefaultAddressByOpenid(NormalUserEntity normalUserEntity){
        if (normalUserEntity == null) return null;
        NormalUserAddressEntity n =  normalUserDao.getUsersDefaultAddressByOpenid(normalUserEntity.getOpenid());
        if (n == null) return null;

        return copyNormalUserAddressEntityToDTO(n);

    }

    public LabelValueTreeEntity getCitiesAndItsProvinces(){
        List<BaseAddressEntity> citiesAndProvinces = normalUserDao.getCitysAndItsProvinces(new BaseAddressEntity());

        return makeTree(citiesAndProvinces,0L);
    }

    public LabelValueTreeEntity getCountriesAndItsTownsByCityId(Long cityId){
        //加载country
        BaseAddressEntity baseAddressEntityForCountry = new BaseAddressEntity();
        baseAddressEntityForCountry.setParentId(cityId);
        List<BaseAddressEntity> countriesAndItsTowns = normalUserDao.getCountriesByCityId(baseAddressEntityForCountry);

        //加载country下的town
        List<Long> countryIds = new ArrayList<>();
        List<BaseAddressEntity> towns = new ArrayList<>();
        for (BaseAddressEntity country : countriesAndItsTowns){
            if (!countryIds.contains(country.getId())) {
                countryIds.add(country.getId());
                BaseAddressEntity baseAddressEntityForTown = new BaseAddressEntity();
                baseAddressEntityForTown.setParentId(country.getId());
                List<BaseAddressEntity> tempTowns = normalUserDao.getTownsByCountryId(baseAddressEntityForTown);
                towns.addAll(tempTowns);
            }
        }

        //加入根元素（city）
        countriesAndItsTowns.addAll(towns);
        BaseAddressEntity cityEntity = getAddressEntityById(cityId);
        countriesAndItsTowns.add(cityEntity);

        //排列为树
        return makeTree(countriesAndItsTowns,cityId);
    }

    public LabelValueTreeEntity getCommuntiesByTownId(Long townId){
        BaseAddressEntity baseAddressEntity = new BaseAddressEntity();
        baseAddressEntity.setParentId(townId);
        List<BaseAddressEntity> communities = normalUserDao.getCommunitiesByTownId(baseAddressEntity);

        //加入根元素（town）
        BaseAddressEntity townEntity = getAddressEntityById(townId);
        communities.add(townEntity);


        return makeTree(communities,townId);
    }

    private LabelValueTreeEntity makeTree(List<BaseAddressEntity> baseAddressEntityList,Long rootId){
        LabelValueTreeEntity tree = new LabelValueTreeEntity();
        Map<Long,LabelValueTreeEntity> map = new HashMap<>();

        List<LabelValueTreeEntity> rootList = new ArrayList<>();
        for (BaseAddressEntity address:baseAddressEntityList){
            LabelValueTreeEntity temp = new LabelValueTreeEntity();
            temp.setId(address.getId());
            temp.setParentId(address.getParentId());
            temp.setLabel(address.getShortName());
            temp.setValue(address.getCode());
            map.put(address.getId(),temp);
        }

        for (LabelValueTreeEntity node : map.values()){
            Long parentId = node.getParentId();
            if (parentId.equals(rootId)){
                tree = node;
            }
            if (map.containsKey(parentId)){
                LabelValueTreeEntity parentNode = map.get(parentId);
                parentNode.addChildren(node);
            }
        }

        return tree;
    }

    public BaseAddressEntity getAddressEntityById(Long id){
        BaseAddressEntity baseAddressEntity = new BaseAddressEntity();
        baseAddressEntity.setId(id);

        return normalUserDao.getAddressEntityById(baseAddressEntity);
    }

    private NormalUserAddressDTO copyNormalUserAddressEntityToDTO(NormalUserAddressEntity n){
        NormalUserAddressDTO normalUserAddressDTO = new NormalUserAddressDTO();
        normalUserAddressDTO.setProvince(n.getProvinceEntity().getShortName());
        normalUserAddressDTO.setCity(n.getCityEntity().getShortName());
        normalUserAddressDTO.setCountry(n.getCountryOrDistrictEntity().getShortName());
        normalUserAddressDTO.setTown(n.getTownEntity().getShortName());
        normalUserAddressDTO.setCommunity(n.getCommunityEntity().getShortName());
        normalUserAddressDTO.setBuilding(n.getBuildingEntity().getShortName());
        normalUserAddressDTO.setUnit(n.getUnitEntity().getShortName());
        normalUserAddressDTO.setRoom(n.getRoomEntity().getShortName());

        return normalUserAddressDTO;
    }


}
