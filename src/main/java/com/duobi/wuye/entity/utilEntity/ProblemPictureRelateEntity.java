package com.duobi.wuye.entity.utilEntity;

public class ProblemPictureRelateEntity extends BaseEntity{

    private Long problemId;
    private String url;

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
