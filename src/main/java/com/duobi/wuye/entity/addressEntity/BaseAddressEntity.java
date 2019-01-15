package com.duobi.wuye.entity.addressEntity;

import com.duobi.wuye.entity.utilEntity.BaseEntity;

public class BaseAddressEntity extends BaseEntity {

    private String name;
    private String code;
    private String shortName;
    private Long parentId;
    private String type;      //01-省 02-市 03-区或县 04-镇 05-小区 06-楼号 07-单元号 08-房间号

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static final String ADDRESS_ENTITY_TYPE_PROVINCE = "01";
    public static final String ADDRESS_ENTITY_TYPE_CITY = "02";
    public static final String ADDRESS_ENTITY_TYPE_COUNTRY = "03";
    public static final String ADDRESS_ENTITY_TYPE_TOWN = "04";
    public static final String ADDRESS_ENTITY_TYPE_COMMUNITY = "05";
    public static final String ADDRESS_ENTITY_TYPE_BUILDING = "06";
    public static final String ADDRESS_ENTITY_TYPE_UNIT = "07";
    public static final String ADDRESS_ENTITY_TYPE_ROOM = "08";
}
