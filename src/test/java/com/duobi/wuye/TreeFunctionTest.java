package com.duobi.wuye;

import com.duobi.wuye.entity.addressEntity.NormalUserAddressEntity;
import com.duobi.wuye.entity.utilEntity.LabelValueTreeEntity;
import com.duobi.wuye.entity.utilEntity.Pager;
import com.duobi.wuye.service.NormalUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TreeFunctionTest {

    private static Logger logger = LoggerFactory.getLogger(TreeFunctionTest.class);
    @Autowired
    private NormalUserService normalUserService;

    @Test
    public void test(){
//        List<BaseAddressEntity> baseAddressEntityList =  normalUserDao.getCitysAndItsProvinces(new BaseAddressEntity());
//        LabelValueTreeEntity labelValueTreeEntity = normalUserService.getCitiesAndItsProvinces();
//        LabelValueTreeEntity labelValueTreeEntity = normalUserService.getCountriesAndItsTownsByCityId(3L);
        LabelValueTreeEntity labelValueTreeEntity = normalUserService.getBuildingsAndItsUnitsAndRoomsByCommunityId(6L);
        logger.info("结果是：{}",labelValueTreeEntity);
    }

    @Test
    public void testPageHelper(){
        Pager<NormalUserAddressEntity> pager  = new Pager<>();
        pager.setOrderBy("id desc");
        pager.setPageNo(1);
        pager.setPageSize(1);

        Pager list = normalUserService.getUsersAddressListByNormalUserId(pager,1L);
        logger.info("结果是: {}",list);

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

    @Test
    public void urlencode(){
        try {
            System.out.println(java.net.URLEncoder.encode("https://duobifuwu-1252535629.cos.ap-beijing.myqcloud.com/wuye/思考人生.jpg8081861990446830822-temp","utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void uuid(){
        for(int i=0;i<10;i++){
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(uuid);
        }
    }


}
