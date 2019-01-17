package com.duobi.wuye.dto;

import com.duobi.wuye.entity.NormalUserEntity;
import com.duobi.wuye.entity.addressEntity.*;
import org.springframework.beans.BeanUtils;

public class NormalUserAddressFromClientDTO extends BaseDTO implements DTOConverterForTwo<NormalUserAddressFromClientDTO, NormalUserAddressEntity,NormalUserEntity> {

    private String openid;
    private String customerName;
    private String phoneNumber;
    private ProvinceEntity provinceEntity;
    private CityEntity cityEntity;
    private CountryOrDistrictEntity countryOrDistrictEntity;
    private TownEntity townEntity;
    private CommunityEntity communityEntity;
    private BuildingEntity buildingEntity;
    private UnitEntity unitEntity;
    private RoomEntity roomEntity;
    private Boolean normalUsersDefaultAddress;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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

    public ProvinceEntity getProvinceEntity() {
        return provinceEntity;
    }

    public void setProvinceEntity(ProvinceEntity provinceEntity) {
        this.provinceEntity = provinceEntity;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public CountryOrDistrictEntity getCountryOrDistrictEntity() {
        return countryOrDistrictEntity;
    }

    public void setCountryOrDistrictEntity(CountryOrDistrictEntity countryOrDistrictEntity) {
        this.countryOrDistrictEntity = countryOrDistrictEntity;
    }

    public TownEntity getTownEntity() {
        return townEntity;
    }

    public void setTownEntity(TownEntity townEntity) {
        this.townEntity = townEntity;
    }

    public CommunityEntity getCommunityEntity() {
        return communityEntity;
    }

    public void setCommunityEntity(CommunityEntity communityEntity) {
        this.communityEntity = communityEntity;
    }

    public BuildingEntity getBuildingEntity() {
        return buildingEntity;
    }

    public void setBuildingEntity(BuildingEntity buildingEntity) {
        this.buildingEntity = buildingEntity;
    }

    public UnitEntity getUnitEntity() {
        return unitEntity;
    }

    public void setUnitEntity(UnitEntity unitEntity) {
        this.unitEntity = unitEntity;
    }

    public RoomEntity getRoomEntity() {
        return roomEntity;
    }

    public void setRoomEntity(RoomEntity roomEntity) {
        this.roomEntity = roomEntity;
    }

    public Boolean getNormalUsersDefaultAddress() {
        return normalUsersDefaultAddress;
    }

    public void setNormalUsersDefaultAddress(Boolean normalUsersDefaultAddress) {
        this.normalUsersDefaultAddress = normalUsersDefaultAddress;
    }

    @Override
    public NormalUserAddressEntity convertOne(NormalUserAddressFromClientDTO o) {
        NormalUserAddressEntity normalUserAddressEntity = new NormalUserAddressEntity();
        BeanUtils.copyProperties(o,normalUserAddressEntity);
        return normalUserAddressEntity;
    }

    @Override
    public NormalUserEntity convertTwo(NormalUserAddressFromClientDTO o){
        NormalUserEntity normalUserEntity = new NormalUserEntity();
        BeanUtils.copyProperties(o,normalUserEntity);
        return normalUserEntity;
    }
}
