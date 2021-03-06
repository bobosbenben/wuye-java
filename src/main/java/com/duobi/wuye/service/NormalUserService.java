package com.duobi.wuye.service;

import com.duobi.wuye.dao.NormalUserDao;
import com.duobi.wuye.dto.NormalUserAddressDTO;
import com.duobi.wuye.entity.NormalUserEntity;
import com.duobi.wuye.entity.addressEntity.BaseAddressEntity;
import com.duobi.wuye.entity.addressEntity.NormalUserAddressEntity;
import com.duobi.wuye.entity.utilEntity.LabelValueTreeEntity;
import com.duobi.wuye.entity.utilEntity.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 根据openid获取用户的默认地址
     * @param normalUserEntity
     * @return
     */
    public NormalUserAddressDTO getUserDefaultAddressByOpenid(NormalUserEntity normalUserEntity){
        if (normalUserEntity == null) return null;
        NormalUserAddressEntity n =  normalUserDao.getUsersDefaultAddressByOpenid(normalUserEntity.getOpenid());
        if (n == null) return null;

        return copyNormalUserAddressEntityToDTO(n);

    }

    /**
     * 获取全部省和城市
     * @return
     */
    public LabelValueTreeEntity getCitiesAndItsProvinces(){
        List<BaseAddressEntity> citiesAndProvinces = normalUserDao.getCitysAndItsProvinces(new BaseAddressEntity());

        //加入根元素（nation）
        BaseAddressEntity cityEntity = getAddressEntityById(1L);
        citiesAndProvinces.add(cityEntity);

        return makeTree(citiesAndProvinces,1L);
    }

    /**
     * 获取全部省和城市和区、县
     * @return
     */
    public LabelValueTreeEntity getCountriesAndCitiesAndItsProvinces(){
        List<BaseAddressEntity> citiesAndProvinces = normalUserDao.getCountriesAndCitysAndItsProvinces(new BaseAddressEntity());

        //加入根元素（nation）
        BaseAddressEntity cityEntity = getAddressEntityById(1L);
        citiesAndProvinces.add(cityEntity);

        return makeTree(citiesAndProvinces,1L);
    }

    /**
     * 根据县或区的id获取它包含的全部镇
     * @param cityId
     * @return
     */
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

    /**
     * 根据镇id获取该镇的全部小区
     * @param townId
     * @return
     */
    public LabelValueTreeEntity getCommuntiesByTownId(Long townId){
        BaseAddressEntity baseAddressEntity = new BaseAddressEntity();
        baseAddressEntity.setParentId(townId);
        List<BaseAddressEntity> communities = normalUserDao.getCommunitiesByTownId(baseAddressEntity);

        //加入根元素（town）
        BaseAddressEntity townEntity = getAddressEntityById(townId);
        communities.add(townEntity);


        return makeTree(communities,townId);
    }

    /**
     * 根据区或县的id获取下辖的全部镇和镇的全部小区
     * @param countryId
     * @return
     */
    public LabelValueTreeEntity getCommuntiesByCountryId(Long countryId){
        BaseAddressEntity baseAddressEntity = new BaseAddressEntity();
        baseAddressEntity.setParentId(countryId);
        List<BaseAddressEntity> townsAndItsCommunities = normalUserDao.getTownsByCountryId(baseAddressEntity);

        List<Long> townIds = new ArrayList<>();
        List<BaseAddressEntity> communities = new ArrayList<>();
        for (BaseAddressEntity town: townsAndItsCommunities){
            if (!townIds.contains(town.getId())){
                townIds.add(town.getId());
                BaseAddressEntity baseAddressEntityForCommunity = new BaseAddressEntity();
                baseAddressEntityForCommunity.setParentId(town.getId());
                List<BaseAddressEntity> tempCommunities = normalUserDao.getCommunitiesByTownId(baseAddressEntityForCommunity);
                communities.addAll(tempCommunities);
            }
        }

        townsAndItsCommunities.addAll(communities);
        BaseAddressEntity countryEntity = getAddressEntityById(countryId);
        townsAndItsCommunities.add(countryEntity);

        return makeTree(townsAndItsCommunities,countryId);
    }

    /**
     * 根据小区id获取该小区的全部楼号、单元号、房号
     * @param communityId
     * @return
     */
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

    /**
     * 将全部“点”信息组装为树类型
     * @param baseAddressEntityList
     * @param rootId
     * @return
     */
    private LabelValueTreeEntity makeTree(List<BaseAddressEntity> baseAddressEntityList,Long rootId){
        if (baseAddressEntityList == null||baseAddressEntityList.size() == 0) return null;
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

    /**
     * 根据id获取房屋的基础信息
     * @param id
     * @return
     */
    public BaseAddressEntity getAddressEntityById(Long id){
        BaseAddressEntity baseAddressEntity = new BaseAddressEntity();
        baseAddressEntity.setId(id);

        return normalUserDao.getAddressEntityById(baseAddressEntity);
    }

    /**
     * 将客户房屋信息转化为前端需要的格式
     * @param n
     * @return
     */
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

    /**
     * 判断客户提交的房屋信息是否有效
     * @param normalUser
     * @param normalUserAddress
     * @throws Exception
     */
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

    /**
     * 根据openid获取客户的基础信息
     * @param openid
     * @return
     */
    public NormalUserEntity getNormalUserInfoByOpenid(String openid){
        return normalUserDao.getNormalUserByOpenid(openid);
    }

    /**
     * 客户新增房屋
     * @param normalUserAddress
     * @throws Exception
     */
    public void insertNewNormalUserAddress(NormalUserAddressEntity normalUserAddress) throws Exception{
        if (normalUserAddress == null ||normalUserAddress.getNormalUsersDefaultAddress() == null || normalUserAddress.getNormalUserId() == null) throw new Exception("修改客户地址的“是否默认地址”字段时错误，提供的原始信息不全");
        if (normalUserAddress.getNormalUsersDefaultAddress() == true) { //新增客户地址前，判断新增的地址是否为“默认地址”，如果为是，则将该用户的其他地址的“默认地址”字段都修改为false
            updateUsersDefaultAddressFlagByUserId(normalUserAddress.getNormalUserId());
        }
        normalUserDao.insertNormalUserAddress(normalUserAddress);
    }

    /**
     * 新增一个用户，包含用户的openid，customerName等
     * @param normalUserEntity
     */
    public void insertNewNormalUser(NormalUserEntity normalUserEntity){
        normalUserDao.insertNormalUser(normalUserEntity);
    }

    /**
     * 根据用户的id，将该用户的所有地址“默认地址”字段修改为false
     * @param normalUserId
     */
    public void updateUsersDefaultAddressFlagByUserId(Long normalUserId){
        normalUserDao.updateUsersDefaultAddressFlagByUserId(normalUserId);
    }

    /**
     * 根据客户的id，获取客户的房屋列表
     * @param pager
     * @param normalUserId
     * @return
     */
    public Pager<NormalUserAddressDTO> getUsersAddressListByNormalUserId(Pager pager,Long normalUserId){
        PageHelper.startPage(pager.getPageNo(),pager.getPageSize(),pager.getOrderBy());
        List<NormalUserAddressEntity> list = normalUserDao.getUsersAddressListByNormalUserId(normalUserId);
        List<NormalUserAddressDTO> dtoList = new ArrayList<>();

        NormalUserAddressDTO dto = new NormalUserAddressDTO();
        for (NormalUserAddressEntity entity : list){
            dtoList.add(dto.convert(entity));
        }

        Page<NormalUserAddressEntity> listAddress = (Page<NormalUserAddressEntity>)list;
        Pager<NormalUserAddressDTO> pagerAddress = new Pager<>();
        pagerAddress.setList(dtoList);
        pagerAddress.setCount(listAddress.getTotal());
        pagerAddress.setPageNo(pager.getPageNo());
        pagerAddress.setPageSize(pager.getPageSize());

        return pagerAddress;
    }

    /**
     * 根据客户房屋的id，获取该id房屋的详细信息
     * @param normalUserAddressId
     * @return 适合前端传输的信息
     */
    public NormalUserAddressDTO getUsersAddressByNormalUserAddressId(Long normalUserAddressId){
        NormalUserAddressEntity  n = normalUserDao.getUsersAddressByNormalUserAddressId(normalUserAddressId);
        NormalUserAddressDTO normalUserAddressDTO = new NormalUserAddressDTO();
        return normalUserAddressDTO.convert(n);
    }

    /**
     * 根据客户房屋的id，获取该id房屋的详细信息
     * @param normalUserAddressId
     * @return 包含id的全量信息
     */
    public NormalUserAddressEntity getUsersAddressByNormalUserAddressId2(Long normalUserAddressId){
        NormalUserAddressEntity  n = normalUserDao.getUsersAddressByNormalUserAddressId(normalUserAddressId);
        return n;
    }

    /**
     * 修改我的房屋页面，根据用户当前的地址的详细信息，获取各地址entity的id，用来设置前端页面的地址项的默认值
     * @param n 包含有用户地址信息的详细信息
     * @return
     */
    public Map<String,List<String>> getAddressIdsForDefaultInfo(NormalUserAddressEntity n){
        Map<String, List<String>> defaultAddressInfo = new HashMap<>();
        List<String> defaultProvinceAndCityIds = new ArrayList<>();
        defaultProvinceAndCityIds.add(String.valueOf(n.getProvinceEntity().getId()));
        defaultProvinceAndCityIds.add(String.valueOf(n.getCityEntity().getId()));
        defaultAddressInfo.put("defaultProvinceAndCityIds",defaultProvinceAndCityIds);
        List<String> countryAndTownIds = new ArrayList<>();
        countryAndTownIds.add(String.valueOf(n.getCountryOrDistrictEntity().getId()));
        countryAndTownIds.add(String.valueOf(n.getTownEntity().getId()));
        defaultAddressInfo.put("defaultCountryAndTowns",countryAndTownIds);
        List<String> community = new ArrayList<>();
        community.add(String.valueOf(n.getCommunityEntity().getId()));
        defaultAddressInfo.put("defaultCommunity",community);
        List<String> buildingAndUnitAndRoom = new ArrayList<>();
        buildingAndUnitAndRoom.add(String.valueOf(n.getBuildingEntity().getId()));
        buildingAndUnitAndRoom.add(String.valueOf(n.getUnitEntity().getId()));
        buildingAndUnitAndRoom.add(String.valueOf(n.getRoomEntity().getId()));
        defaultAddressInfo.put("defaultBuildingAndUnitAndRoom",buildingAndUnitAndRoom);

        return defaultAddressInfo;
    }

    /**
     * 删除用户的房屋（set del_flag=false）
     * @param normalUserAddressId
     */
    @Transactional
    public void deleteNormalUserAddressById(Long normalUserAddressId,String openid){

        NormalUserEntity normalUser = getNormalUserInfoByOpenid(openid);
        Long normalUserId = getUserIdByNormalUserAddressId(normalUserAddressId);

        if (normalUser != null && normalUser.getId().equals(normalUserId)) normalUserDao.deleteNormalUserAddressById(normalUserAddressId);
        if (1==getAddressCountByUserId(normalUserId)) updateUsersDefaultAddressTrueByUserId(normalUserId);
    }

    /**
     * 根据房屋的id获取到用户的id，用来判断该房屋是否是该用户的
     * @param normalUserAddressId 房屋的id
     * @return
     */
    public Long getUserIdByNormalUserAddressId(Long normalUserAddressId){
        return normalUserDao.getUserIdByNormalUserAddressId(normalUserAddressId);
    }

    /**
     * 将房屋的默认地址设置为true，用于当用户删除的房屋只剩余1个的时候，将最后一个房屋设置为默认房屋
     * @param normalUserId 用户id
     */
    public void updateUsersDefaultAddressTrueByUserId(Long normalUserId){
        normalUserDao.updateUsersDefaultAddressTrueByUserId(normalUserId);
    }

    /**
     * 获取客户拥有的房屋数
     * @param normalUserId 客户的id
     * @return
     */
    public int getAddressCountByUserId(Long normalUserId){
        return normalUserDao.getAddressCountByUserId(normalUserId);
    }

}
