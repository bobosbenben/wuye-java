package com.duobi.wuye;

import com.duobi.wuye.entity.utilEntity.LabelValueTreeEntity;
import com.duobi.wuye.service.NormalUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TreeFunctionTest {

    private static Logger logger = LoggerFactory.getLogger(TreeFunctionTest.class);
    @Autowired
    private NormalUserService normalUserService;

    @Test
    public void test(){
//        List<BaseAddressEntity> baseAddressEntityList =  normalUserDao.getCitysAndItsProvinces(new BaseAddressEntity());
        LabelValueTreeEntity labelValueTreeEntity = normalUserService.getCountriesAndItsTownsByCityId(2L);
        logger.info("结果是：{}",labelValueTreeEntity);
    }

    @Test
    public void testList(){
        List<LabelValueTreeEntity> list = new ArrayList<>();

        for (int i=0;i<2;i++){
            String temp;
            if (i==0) temp = "1";
            else temp = "2";
            LabelValueTreeEntity labelValueTreeEntity = new LabelValueTreeEntity();
            labelValueTreeEntity.setValue(temp);
            list.add(labelValueTreeEntity);

            logger.info("original: {}",list.get(0).getValue());
        }



        logger.info("now: {}",list.get(0).getValue());
        logger.info("size: {}",list.get(1).getValue());


    }



}
