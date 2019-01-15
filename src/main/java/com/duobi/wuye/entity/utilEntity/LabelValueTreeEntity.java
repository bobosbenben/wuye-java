package com.duobi.wuye.entity.utilEntity;

import java.util.ArrayList;
import java.util.List;

public class LabelValueTreeEntity {

    private Long id;
    private Long parentId;
    private String label;
    private String value;
    private List<LabelValueTreeEntity> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<LabelValueTreeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<LabelValueTreeEntity> children) {
        this.children = children;
    }

    public void addChildren(LabelValueTreeEntity labelValueTreeEntity){
        if (children == null) children = new ArrayList<>();
        children.add(labelValueTreeEntity);
    }
}
