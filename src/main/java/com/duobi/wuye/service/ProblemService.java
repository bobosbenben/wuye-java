package com.duobi.wuye.service;

import com.duobi.wuye.dao.ProblemDao;
import com.duobi.wuye.entity.NormalUserEntity;
import com.duobi.wuye.entity.ProblemEntity;
import com.duobi.wuye.entity.ProblemTypeEntity;
import com.duobi.wuye.entity.utilEntity.ProblemPictureRelateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemService {

    @Autowired
    private ProblemDao problemDao;

    /**
     * 将单条问题关联的多张图片的路径入库
     * @param pictureList
     * @param problemId
     */
    @Transactional
    public void insertProblemRelatedPictureList(List<String> pictureList,Long problemId){

        List<ProblemPictureRelateEntity> list = new ArrayList<>();
        for (String picture:pictureList){
            ProblemPictureRelateEntity entity = new ProblemPictureRelateEntity();
            entity.setProblemId(problemId);
            entity.setUrl(picture);
            list.add(entity);
        }

        problemDao.insertProblemRelatedPictureList(list);
    }

    /**
     * 将客户提交的问题入库
     * @param problemEntity
     */
    @Transactional
    public void insertProblem(ProblemEntity problemEntity){
        problemDao.insertProblem(problemEntity);
        insertProblemRelatedPictureList(problemEntity.getProblemPictureUrl(),problemEntity.getId());
    }

    /**
     * 根据问题类型的简称获取问题类型
     * @param shortName
     * @return
     */
    @Transactional
    public ProblemTypeEntity getProblemTypeByShortName(String shortName){
        return problemDao.getProblemTypeByShortName(shortName);
    }

    /**
     * 根据openid获取用户详细信息
     * @param openid
     * @return
     */
    public NormalUserEntity getNormalUserByOpenid(String openid){
        return problemDao.getNormalUserByOpenid(openid);
    }


}
