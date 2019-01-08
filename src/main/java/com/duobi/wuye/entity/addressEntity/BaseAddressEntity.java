package com.duobi.wuye.entity.addressEntity;

import com.duobi.wuye.entity.BaseEntity;

public class BaseAddressEntity extends BaseEntity {

    private String name;
    private String code;
    private String shorName;

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

    public String getShorName() {
        return shorName;
    }

    public void setShorName(String shorName) {
        this.shorName = shorName;
    }
}
