package com.duobi.wuye.entity;

import com.duobi.wuye.entity.addressEntity.NormalUserCommunityEntity;

import java.util.List;

public class NormalUser extends BaseEntity {

    private String openId;
    private String nickName;
    private String sex;     //1-男  2-女
    private String province;
    private String city;
    private String country;
    private String headImgUrl;
    private String[] privilege;
    private String unionId;
    private List<NormalUserCommunityEntity> normalUserCommunityEntityList;


}
