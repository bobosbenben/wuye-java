package com.duobi.wuye.dao;

import com.duobi.wuye.entity.addressEntity.BaseAddressEntity;
import com.duobi.wuye.entity.addressEntity.NormalUserAddressEntity;

import java.util.List;

public interface NormalUserDao {

    public NormalUserAddressEntity getUsersDefaultAddressByNormalUserId(Long id);

    public NormalUserAddressEntity getUsersDefaultAddressByOpenid(String openid);

    public List<BaseAddressEntity> getCitysAndItsProvinces(BaseAddressEntity baseAddressEntity);

    public List<BaseAddressEntity> getCountriesByCityId(BaseAddressEntity baseAddressEntity);

    public List<BaseAddressEntity> getTownsByCountryId(BaseAddressEntity baseAddressEntity);

    public List<BaseAddressEntity> getCommunitiesByTownId(BaseAddressEntity baseAddressEntity);

    public List<BaseAddressEntity> getBuildingsByCommunityId(BaseAddressEntity baseAddressEntity);

    public List<BaseAddressEntity> getUnitsByBuildingId(BaseAddressEntity baseAddressEntity);

    public List<BaseAddressEntity> getRoomsByUnitId(BaseAddressEntity baseAddressEntity);

    public BaseAddressEntity getAddressEntityById(BaseAddressEntity baseAddressEntity);


}
