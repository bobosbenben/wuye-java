package com.duobi.wuye.dao;

import com.duobi.wuye.entity.addressEntity.NormalUserAddressEntity;

public interface NormalUserDao {

    public NormalUserAddressEntity getUsersDefaultAddressByNormalUserId(Long id);

}
