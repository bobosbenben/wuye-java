package com.duobi.wuye.controller;

import com.alibaba.fastjson.JSONObject;
import com.duobi.wuye.dto.NormalUserAddressDTO;
import com.duobi.wuye.dto.NormalUserAddressFromClientDTO;
import com.duobi.wuye.entity.NormalUserEntity;
import com.duobi.wuye.entity.addressEntity.NormalUserAddressEntity;
import com.duobi.wuye.entity.utilEntity.LabelValueTreeEntity;
import com.duobi.wuye.entity.utilEntity.Pager;
import com.duobi.wuye.service.NormalUserService;
import com.duobi.wuye.utils.ResponseJson;
import com.duobi.wuye.utils.weixinutils.DataExchangeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("/minecommunity")
public class MineCommunityController {

    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private NormalUserService normalUserService;

    @CrossOrigin( maxAge = 3600)
    @RequestMapping(value = "/add")
    public String addMineCommunity(HttpServletRequest request){
        String code = request.getParameter("code");
        NormalUserEntity userInfo = new NormalUserEntity();

        try {
            userInfo = DataExchangeUtil.getUserInfoByCode(code);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "redirect:https://www.duobifuwu.com/#/addminecommunity/"+userInfo.getOpenid();
        return url;
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping(value = "/new")
    public @ResponseBody
    Object newMineCommunity(@RequestBody NormalUserAddressFromClientDTO dto){
        ResponseJson responseJson = new ResponseJson();

        NormalUserAddressEntity normalUserAddress = dto.convertOne(dto);
        NormalUserEntity normalUser = dto.convertTwo(dto);

        try {
            normalUserService.checkNewNormalUserAddressValidation(normalUser,normalUserAddress);
            NormalUserEntity completeNormalUserInfo = normalUserService.getNormalUserInfoByOpenid(normalUser.getOpenid());
            if (completeNormalUserInfo == null || completeNormalUserInfo.getId() == null) throw new Exception("无法获取客户信息");

            normalUserAddress.setNormalUserId(completeNormalUserInfo.getId());
            normalUserAddress.setCreateBy(completeNormalUserInfo.getId());
            normalUserService.insertNewNormalUserAddress(normalUserAddress);
        }catch (Exception e){
            e.printStackTrace();
            responseJson.setSuccess(false);
            responseJson.setMsg(e.getMessage());
            return responseJson;
        }

        return responseJson;
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping(value = "/getdistrict")
    public @ResponseBody
    Object getDistrictByOpenid(){
        LabelValueTreeEntity labelValueTreeEntity = normalUserService.getCitiesAndItsProvinces();

        if (null == labelValueTreeEntity) return new ArrayList<>();
        return labelValueTreeEntity.getChildren();
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping(value = "/getcountry")
    public @ResponseBody
    Object getCountryAndTownByCityId(@RequestBody JSONObject jsonObject){

        Long cityId = Long.parseLong(jsonObject.getString("cityId"));
        LabelValueTreeEntity labelValueTreeEntity = normalUserService.getCountriesAndItsTownsByCityId(cityId);

        if (null == labelValueTreeEntity) return new ArrayList<>();
        return labelValueTreeEntity.getChildren();
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping(value = "/getcommunity")
    public @ResponseBody
    Object getCommunityByTownId(@RequestBody JSONObject jsonObject){

        Long townId = Long.parseLong(jsonObject.getString("townId"));
        LabelValueTreeEntity labelValueTreeEntity = normalUserService.getCommuntiesByTownId(townId);

        if (null == labelValueTreeEntity) return new ArrayList<>();
        return labelValueTreeEntity.getChildren();
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping(value = "/getroom")
    public @ResponseBody
    Object getRoomByCommunityId(@RequestBody JSONObject jsonObject){

        Long communityId = Long.parseLong(jsonObject.getString("communityId"));
        LabelValueTreeEntity labelValueTreeEntity = normalUserService.getBuildingsAndItsUnitsAndRoomsByCommunityId(communityId);

        if (null == labelValueTreeEntity) return new ArrayList<>();
        return labelValueTreeEntity.getChildren();
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping(value = "/getminehouselist")
    public @ResponseBody
    Object getMineHouseList(@RequestBody JSONObject jsonObject){
        ResponseJson responseJson = new ResponseJson();

        String openid = jsonObject.getString("openid");
        Pager pager = jsonObject.getObject("page",Pager.class);
        try {
            NormalUserEntity completeNormalUserInfo = normalUserService.getNormalUserInfoByOpenid(openid);
            if (completeNormalUserInfo == null || completeNormalUserInfo.getId() == null) throw new Exception("无法获取客户信息");
            Pager<NormalUserAddressDTO> addressPager = normalUserService.getUsersAddressListByNormalUserId(pager,completeNormalUserInfo.getId());
            responseJson.setSuccess(true);
            responseJson.setTotal((int)addressPager.getCount());
            responseJson.setData(addressPager.getList());
        }catch (Exception e){
            responseJson.setSuccess(false);
            responseJson.setMsg(e.getMessage());
            responseJson.setTotal(0);
            return responseJson;
        }

        return responseJson;
    }



}
