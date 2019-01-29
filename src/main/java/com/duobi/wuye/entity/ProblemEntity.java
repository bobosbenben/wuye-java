package com.duobi.wuye.entity;

import com.duobi.wuye.entity.utilEntity.BaseEntity;

import java.util.Date;
import java.util.List;

public class ProblemEntity extends BaseEntity {

    private String openid;
    private NormalUserEntity normalUser;
    private ProblemTypeEntity problemType;
    private String problemDescription;
    private List<String> problemPictureUrl;
    private Date appointmentTime;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public NormalUserEntity getNormalUser() {
        return normalUser;
    }

    public void setNormalUser(NormalUserEntity normalUser) {
        this.normalUser = normalUser;
    }

    public ProblemTypeEntity getProblemType() {
        return problemType;
    }

    public void setProblemType(ProblemTypeEntity problemType) {
        this.problemType = problemType;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public List<String> getProblemPictureUrl() {
        return problemPictureUrl;
    }

    public void setProblemPictureUrl(List<String> problemPictureUrl) {
        this.problemPictureUrl = problemPictureUrl;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
