package com.duobi.wuye.entity;

import com.duobi.wuye.entity.utilEntity.BaseEntity;

public class ProblemTypeEntity extends BaseEntity {

    private String name;
    private String shortName;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
