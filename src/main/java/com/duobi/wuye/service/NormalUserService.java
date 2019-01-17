package com.duobi.wuye.service;

import com.duobi.wuye.dao.NormalUserDao;
import com.duobi.wuye.dto.NormalUserAddressDTO;
import com.duobi.wuye.entity.NormalUserEntity;
import com.duobi.wuye.entity.addressEntity.BaseAddressEntity;
import com.duobi.wuye.entity.addressEntity.NormalUserAddressEntity;
import com.duobi.wuye.entity.utilEntity.LabelValueTreeEntity;
import org.apache.commons.lang3.StringUtils;
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

        //加入根元素（nation）
        BaseAddressEntity cityEntity = getAddressEntityById(1L);
        citiesAndProvinces.add(cityEntity);

        return makeTree(citiesAndProvinces,1L);
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

    public LabelValueTreeEntity getBuildingsAndItsUnitsAndRoomsByCommunityId(Long communityId){
        //加载building
        BaseAddressEntity baseAddressEntity = new BaseAddressEntity();
        baseAddressEntity.setParentId(communityId);
        List<BaseAddressEntity> buildingsAndUnitsAndRooms = normalUserDao.getBuildingsByCommunityId(baseAddressEntity);

        //加载building下的units
        List<Long> buildingIds = new ArrayList<>();
        List<BaseAddressEntity> units = new ArrayList<>();
        for (BaseAddressEntity building : buildingsAndUnitsAndRooms){
            if (!buildingIds.contains(building.getId())){
                buildingIds.add(building.getId());
                BaseAddressEntity baseAddressEntityForUnit = new BaseAddressEntity();
                baseAddressEntityForUnit.setParentId(building.getId());
                List<BaseAddressEntity> tempUnits = normalUserDao.getUnitsByBuildingId(baseAddressEntityForUnit);
                units.addAll(tempUnits);
            }
        }

        //加载unit下的room
        List<Long> unitIds = new ArrayList<>();
        List<BaseAddressEntity> rooms = new ArrayList<>();
        for (BaseAddressEntity unit : units){
            if (!unitIds.contains(unit.getId())){
                unitIds.add(unit.getId());
                BaseAddressEntity baseAddressEntityForRoom = new BaseAddressEntity();
                baseAddressEntityForRoom.setParentId(unit.getId());
                List<BaseAddressEntity> tempRooms = normalUserDao.getRoomsByUnitId(baseAddressEntityForRoom);
                rooms.addAll(tempRooms);
            }
        }

        //加入根元素（city）
        buildingsAndUnitsAndRooms.addAll(units);
        buildingsAndUnitsAndRooms.addAll(rooms);
        BaseAddressEntity communityEntity = getAddressEntityById(communityId);
        buildingsAndUnitsAndRooms.add(communityEntity);

        //排列为树
        return makeTree(buildingsAndUnitsAndRooms,communityId);
    }

    private LabelValueTreeEntity makeTree(List<BaseAddressEntity> baseAddressEntityList,Long rootId){
        LabelValueTreeEntity tree = null;
        Map<Long,LabelValueTreeEntity> map = new HashMap<>();

        for (BaseAddressEntity address:baseAddressEntityList){
            LabelValueTreeEntity temp = new LabelValueTreeEntity();
            temp.setId(address.getId());
            temp.setParentId(address.getParentId());
            temp.setLabel(address.getShortName());
//            temp.setValue(address.getCode());     //code不唯一，因为不同小区的楼号可能都是5号楼，code就都是'5'，所以改用id
            temp.setValue(address.getId().toString());
            map.put(address.getId(),temp);
        }

        for (LabelValueTreeEntity node : map.values()){
            Long parentId = node.getParentId();
            Long id = node.getId();
            if (id.equals(rootId)){
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

    public void checkNewNormalUserAddressValidation(NormalUserEntity normalUser, NormalUserAddressEntity normalUserAddress) throws Exception{

        if (StringUtils.isBlank(normalUser.getOpenid())) {
            throw new Exception("无法识别的客户");
        }
        if (StringUtils.isBlank(normalUserAddress.getCustomerName())){
            throw new Exception("请填写您的称呼");
        }
        if (StringUtils.isBlank(normalUserAddress.getPhoneNumber())){
            throw new Exception("请填写您的联系方式");
        }
        if (null == normalUserAddress.getProvinceEntity() || null == normalUserAddress.getProvinceEntity().getId()){
            throw new Exception("客户地址中未设置省份");
        }
        if (null == normalUserAddress.getCityEntity() || null == normalUserAddress.getCityEntity().getId()){
            throw new Exception("客户地址中未设置城市");
        }
        if (null == normalUserAddress.getCountryOrDistrictEntity() || null == normalUserAddress.getCountryOrDistrictEntity().getId()){
            throw new Exception("客户地址中未设置县或区");
        }
        if (null == normalUserAddress.getTownEntity() || null == normalUserAddress.getTownEntity().getId()){
            throw new Exception("客户地址中未设置镇或街道");
        }
        if (null == normalUserAddress.getCommunityEntity() || null == normalUserAddress.getCommunityEntity().getId()){
            throw new Exception("客户地址中未设置小区");
        }
        if (null == normalUserAddress.getBuildingEntity() || null == normalUserAddress.getBuildingEntity().getId()){
            throw new Exception("客户地址中未设置楼号");
        }
        if (null == normalUserAddress.getUnitEntity() || null == normalUserAddress.getUnitEntity().getId()){
            throw new Exception("客户地址中未设置单元号");
        }
        if (null == normalUserAddress.getRoomEntity() || null == normalUserAddress.getRoomEntity().getId()){
            throw new Exception("客户地址中未设置房间号");
        }

    }

    public NormalUserEntity getNormalUserInfoByOpenid(String openid){
        return normalUserDao.getNormalUserByOpenid(openid);
    }

    public void insertNewNormalUserAddress(NormalUserAddressEntity normalUserAddress){
        normalUserDao.insertNormalUserAddress(normalUserAddress);
    }


}
