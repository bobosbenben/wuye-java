package com.duobi.wuye.controller;

import com.alibaba.fastjson.JSONObject;
import com.duobi.wuye.dto.NormalUserAddressDTO;
import com.duobi.wuye.entity.NormalUserEntity;
import com.duobi.wuye.service.NormalUserService;
import com.duobi.wuye.utils.CosClientUtil;
import com.duobi.wuye.utils.ResponseJson;
import com.duobi.wuye.utils.weixinutils.DataExchangeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping("/wuye")
public class WuYeController {

    private static Logger logger = LoggerFactory.getLogger(WuYeController.class);

    @Autowired
    private NormalUserService normalUserService;

    @CrossOrigin( maxAge = 3600)
    @RequestMapping("/getdefaultaddress")
    public @ResponseBody
    Object getNormalUsersDefaultAddress(@RequestBody JSONObject jsonObject){
        ResponseJson responseJson = new ResponseJson();

        String openid = jsonObject.getString("openid");

        NormalUserEntity normalUserEntity = new NormalUserEntity();
        normalUserEntity.setOpenid(openid);
        NormalUserAddressDTO n = normalUserService.getUserDefaultAddressByOpenid(normalUserEntity);

        responseJson.setSuccess(true);
        if (n != null) responseJson.setTotal(1);
        else responseJson.setTotal(0);
        responseJson.setData(n);

        return responseJson;
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping("/getaddress")
    public @ResponseBody
    Object getNormalUsersAddressById(@RequestBody JSONObject jsonObject){
        ResponseJson responseJson = new ResponseJson();

        String openid = jsonObject.getString("openid");
        Long normalUserAddressId = jsonObject.getLong("normalUserAddressId");

        NormalUserAddressDTO n = normalUserService.getUsersAddressByNormalUserAddressId(normalUserAddressId);
        responseJson.setSuccess(true);
        responseJson.setData(n);
        if (n != null) responseJson.setTotal(1);
        else responseJson.setTotal(0);

        return responseJson;
    }

    @CrossOrigin( maxAge = 3600)
    @RequestMapping(value = "/faultreport")
    public String faultReport(HttpServletRequest request){
        String code = request.getParameter("code");
        NormalUserEntity userInfo = null;

        try {
            userInfo = DataExchangeUtil.getUserInfoByCode(code);
            if (userInfo == null || userInfo.getOpenid() == null) throw new Exception("通过code获取客户的openid失败");
            NormalUserEntity normalUser = normalUserService.getNormalUserInfoByOpenid(userInfo.getOpenid());
            //判断该openid的客户是否已经存在，不存在时入库
            if (normalUser == null) normalUserService.insertNewNormalUser(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //这里应该新增一个前端的错误处理页面，当userInfo==null 的时候跳转过去

        String url = "redirect:https://www.duobifuwu.com/#/baoxiu/"+userInfo.getOpenid();
        return url;
    }

}
