package com.duobi.wuye.entity.addressEntity;

import com.duobi.wuye.entity.BaseEntity;

public class BaseAddressEntity extends BaseEntity {

    private String name;
    private String code;
    private String shortName;

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

    public void setShorName(String shorName) {
        this.shortName = shorName;
    }
}
