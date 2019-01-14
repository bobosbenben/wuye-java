package com.duobi.wuye.entity;

import com.duobi.wuye.entity.addressEntity.NormalUserAddressEntity;

import java.util.List;

public class NormalUser extends BaseEntity {

    private String openid;
    private String nickName;
    private String sex;     //1-男  2-女
    private String province;
    private String city;
    private String country;
    private String headImgUrl;
    private String[] privilege;
    private String unionid;
    private List<NormalUserAddressEntity> normalUserAddressEntityList;

    public NormalUser(){}

    public NormalUser(Long id){
        this.setId(id);
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String[] getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String[] privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<NormalUserAddressEntity> getNormalUserAddressEntityList() {
        return normalUserAddressEntityList;
    }

    public void setNormalUserAddressEntityList(List<NormalUserAddressEntity> normalUserAddressEntityList) {
        this.normalUserAddressEntityList = normalUserAddressEntityList;
    }

}
