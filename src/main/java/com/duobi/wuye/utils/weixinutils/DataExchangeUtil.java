package com.duobi.wuye.utils.weixinutils;

import com.alibaba.fastjson.JSONObject;

public class DataExchangeUtil {

    public static String getOpenIdByCode(String code) throws Exception {
        String openid = null;
        if (code == null || code.equals("")) throw new Exception("从前台获取的code为空");
        else {
            String url = AppConifg.get_openid_by_code_url;
            url = url.replace("APPID", AppConifg.appid);
            url = url.replace("SECRET",AppConifg.secret);
            url = url.replace("CODE",code);

            String responseStr = HttpClientUtil.doGet(url);

            JSONObject jsonObject = JSONObject.parseObject(responseStr);
            openid = jsonObject.getString("openid");
            System.out.println("获取的返回字符串是："+responseStr);
            System.out.println("获取的openid是："+openid);
        }
        return openid;
    }


}
