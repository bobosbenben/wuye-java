package com.duobi.wuye.entity.addressEntity;

import com.duobi.wuye.entity.BaseEntity;

public class NormalUserAddressEntity extends BaseEntity {

    private ProvinceEntity provinceEntity;                      //省
    private CityEntity cityEntity;                              //市
    private CountryOrDistrictEntity countryOrDistrictEntity;    //县、区、旗
    private TownEntity townEntity;                              //镇
    private CommunityEntity communityEntity;                    //小区
    private BuildingEntity buildingEntity;                      //楼号
    private UnitEntity unitEntity;                              //单元
    private RoomEntity roomEntity;                              //房间号
    private Long normalUserId;                                  //所属用户的id
    private Boolean normalUsersDefaultAddress;                  //是否是所属用户的默认小区

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

    public Long getNormalUserId() {
        return normalUserId;
    }

    public void setNormalUserId(Long normalUserId) {
        this.normalUserId = normalUserId;
    }

    public Boolean getNormalUsersDefaultAddress() {
        return normalUsersDefaultAddress;
    }

    public void setNormalUsersDefaultAddress(Boolean normalUsersDefaultAddress) {
        this.normalUsersDefaultAddress = normalUsersDefaultAddress;
    }
}
