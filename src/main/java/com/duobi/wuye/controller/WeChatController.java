package com.duobi.wuye.controller;

import com.duobi.wuye.utils.weixinutils.WeChatCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WeChatController {

    private Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @RequestMapping(value ="/wx")
    public @ResponseBody
    String checkSignature(HttpServletRequest req, HttpServletResponse res){

        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        try {
            if (WeChatCheckUtil.checkSignature(signature,timestamp,nonce,"shiyibo")) logger.info("echostr: {}",echostr);
            return echostr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
