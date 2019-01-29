package com.duobi.wuye.dao;

import com.duobi.wuye.entity.NormalUserEntity;
import com.duobi.wuye.entity.ProblemEntity;
import com.duobi.wuye.entity.ProblemTypeEntity;
import com.duobi.wuye.entity.utilEntity.ProblemPictureRelateEntity;

import java.util.List;

public interface ProblemDao {

    public void insertProblemRelatedPictureList(List<ProblemPictureRelateEntity> list);

    public void insertProblem(ProblemEntity problemEntity);

    public ProblemTypeEntity getProblemTypeByShortName(String shortName);

    public NormalUserEntity getNormalUserByOpenid(String openid);

}
