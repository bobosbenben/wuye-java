package com.duobi.wuye.controller;

import com.alibaba.fastjson.JSONObject;
import com.duobi.wuye.dto.NormalUserAddressFromClientDTO;
import com.duobi.wuye.entity.NormalUserEntity;
import com.duobi.wuye.entity.addressEntity.NormalUserAddressEntity;
import com.duobi.wuye.entity.utilEntity.LabelValueTreeEntity;
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

@Controller
@RequestMapping("/minecommunity")
public class MineCommunityController {

    private static Logger logger = LoggerFactory.getLogger(MineCommunityController.class);

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
            normalUserAddress.setNormalUserId(completeNormalUserInfo.getId());
            normalUserService.insertNewNormalUserAddress(normalUserAddress);
        }catch (Exception e){
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

        return labelValueTreeEntity.getChildren();
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping(value = "/getcountry")
    public @ResponseBody
    Object getCountryAndTownByCityId(@RequestBody JSONObject jsonObject){

        Long cityId = Long.parseLong(jsonObject.getString("cityId"));
        LabelValueTreeEntity labelValueTreeEntity = normalUserService.getCountriesAndItsTownsByCityId(cityId);

        return labelValueTreeEntity.getChildren();
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping(value = "/getcommunity")
    public @ResponseBody
    Object getCommunityByTownId(@RequestBody JSONObject jsonObject){

        Long townId = Long.parseLong(jsonObject.getString("townId"));
        LabelValueTreeEntity labelValueTreeEntity = normalUserService.getCommuntiesByTownId(townId);

        return labelValueTreeEntity.getChildren();
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping(value = "/getroom")
    public @ResponseBody
    Object getRoomByCommunityId(@RequestBody JSONObject jsonObject){

        Long communityId = Long.parseLong(jsonObject.getString("communityId"));
        LabelValueTreeEntity labelValueTreeEntity = normalUserService.getBuildingsAndItsUnitsAndRoomsByCommunityId(communityId);

        return labelValueTreeEntity.getChildren();
    }



}
