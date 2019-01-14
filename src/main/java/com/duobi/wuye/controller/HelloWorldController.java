package com.duobi.wuye.controller;

import com.duobi.wuye.entity.User;
import com.duobi.wuye.service.TestService;
import com.duobi.wuye.utils.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
@CrossOrigin
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    public @ResponseBody Object index() {
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

    @RequestMapping("/xinxi")
    public ModelAndView index2() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index.html");

        return modelAndView;
    }

    @RequestMapping("/redirect")
    public String redirect() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/index.html");

        String url = "redirect:https://www.duobifuwu.com/#/baoxiu/oLb0h1kMIeuxBV8DnVPUM8XVPNck";
        return url;
    }
}