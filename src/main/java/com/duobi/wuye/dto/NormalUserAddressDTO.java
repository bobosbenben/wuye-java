package com.duobi.wuye.dto;

import com.duobi.wuye.entity.addressEntity.NormalUserAddressEntity;

public class NormalUserAddressDTO extends BaseDTO implements DTOConverterForOne<NormalUserAddressEntity,NormalUserAddressDTO>{

    private Long id;
    private String province;
    private String city;
    private String country;
    private String town;
    private String community;
    private String building;
    private String unit;
    private String room;
    private String communityImgUrl;
    private String customerName;
    private String phoneNumber;
    private Boolean normalUsersDefaultAddress;

    public String getProvince() {
        return province;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCommunityImgUrl() {
        return communityImgUrl;
    }

    public void setCommunityImgUrl(String communityImgUrl) {
        this.communityImgUrl = communityImgUrl;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getNormalUsersDefaultAddress() {
        return normalUsersDefaultAddress;
    }

    public void setNormalUsersDefaultAddress(Boolean normalUsersDefaultAddress) {
        this.normalUsersDefaultAddress = normalUsersDefaultAddress;
    }

    @Override
    public NormalUserAddressDTO convert(NormalUserAddressEntity o) {
        NormalUserAddressDTO dto = new NormalUserAddressDTO();
        dto.setId(o.getId());

        dto.setProvince(o.getProvinceEntity().getShortName());
        dto.setCity(o.getCityEntity().getShortName());
        dto.setCountry(o.getCountryOrDistrictEntity().getShortName());
        dto.setTown(o.getTownEntity().getShortName());
        dto.setCommunity(o.getCommunityEntity().getShortName());
        dto.setBuilding(o.getBuildingEntity().getShortName());
        dto.setUnit(o.getUnitEntity().getShortName());
        dto.setRoom(o.getRoomEntity().getShortName());
        dto.setCommunityImgUrl(o.getCommunityEntity().getImgUrl());
        dto.setCustomerName(o.getCustomerName());
        dto.setPhoneNumber(o.getPhoneNumber());
        dto.setNormalUsersDefaultAddress(o.getNormalUsersDefaultAddress());

        return dto;
    }
}
