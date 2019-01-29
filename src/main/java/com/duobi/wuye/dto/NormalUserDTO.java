package com.duobi.wuye.dto;

import com.duobi.wuye.entity.NormalUserEntity;
import org.springframework.beans.BeanUtils;

public class NormalUserDTO extends BaseDTO implements DTOConverterForOne<NormalUserEntity,NormalUserDTO> {

    private String openid;
    private String nickName;
    private String sex;
    private String nation;
    private String city;
    private String headImgUrl;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if (sex.equals("1"))
        this.sex = "男";
        else this.sex = "女";
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    @Override
    public NormalUserDTO convert(NormalUserEntity o) {
        if (o==null) return null;
        NormalUserDTO dto = new NormalUserDTO();
        BeanUtils.copyProperties(o,dto);
        return dto;
    }
}
