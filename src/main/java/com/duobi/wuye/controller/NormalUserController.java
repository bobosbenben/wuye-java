package com.duobi.wuye.controller;

import com.alibaba.fastjson.JSONObject;
import com.duobi.wuye.dto.NormalUserAddressDTO;
import com.duobi.wuye.dto.NormalUserDTO;
import com.duobi.wuye.entity.NormalUserEntity;
import com.duobi.wuye.service.NormalUserService;
import com.duobi.wuye.utils.CosClientUtil;
import com.duobi.wuye.utils.ResponseJson;
import com.duobi.wuye.utils.weixinutils.DataExchangeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/normaluser")
public class NormalUserController {

    private static Logger logger = LoggerFactory.getLogger(NormalUserController.class);

    @Autowired
    private NormalUserService normalUserService;

    @CrossOrigin( maxAge = 3600)
    @RequestMapping("/getinfo")
    public @ResponseBody
    Object getNormalUsersAddressById(@RequestBody JSONObject jsonObject){
        ResponseJson responseJson = new ResponseJson();

        String openid = jsonObject.getString("openid");
        NormalUserEntity n = normalUserService.getNormalUserInfoByOpenid(openid);
        NormalUserDTO dto = new NormalUserDTO();
        dto = dto.convert(n);

        if (dto == null) responseJson.setTotal(0);
        else responseJson.setTotal(1);
        responseJson.setSuccess(true);
        responseJson.setData(dto);
        return responseJson;
    }


}
