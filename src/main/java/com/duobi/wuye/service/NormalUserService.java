package com.duobi.wuye.service;

import com.duobi.wuye.dao.NormalUserDao;
import com.duobi.wuye.dto.NormalUserAddressDTO;
import com.duobi.wuye.entity.NormalUser;
import com.duobi.wuye.entity.addressEntity.NormalUserAddressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NormalUserService {

    @Autowired
    private NormalUserDao normalUserDao;

    public NormalUserAddressDTO getUsersDefaultAddressByNormalUserId(NormalUser normalUser){
        if (normalUser == null) return null;
        NormalUserAddressEntity n =  normalUserDao.getUsersDefaultAddressByNormalUserId(normalUser.getId());
        if (n == null) return null;

        return copyNormalUserAddressEntityToDTO(n);
    }

    public NormalUserAddressDTO getUserDefaultAddressByOpenid(NormalUser normalUser){
        if (normalUser == null) return null;
        NormalUserAddressEntity n =  normalUserDao.getUsersDefaultAddressByOpenid(normalUser.getOpenid());
        if (n == null) return null;

        return copyNormalUserAddressEntityToDTO(n);

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
