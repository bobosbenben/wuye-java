package com.duobi.wuye.controller;

import com.duobi.wuye.entity.User;
import com.duobi.wuye.service.TestService;
import com.duobi.wuye.utils.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@CrossOrigin
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    public Object index() {
        User user = new User();
        user.setId(1L);

        ResponseJson responseJson = new ResponseJson();
        Random random = new Random();
        int i = random.nextInt(2);
        if (i == 0) responseJson.setSuccess(true);
        if (i==1) responseJson.setSuccess(false);
        responseJson.setData(testService.getUserById(user).getName());

        return responseJson;
    }
}