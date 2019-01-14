package com.duobi.wuye.utils.weixinutils;

import com.alibaba.fastjson.JSONObject;
import com.duobi.wuye.entity.NormalUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataExchangeUtil {

    private static Logger logger = LoggerFactory.getLogger(DataExchangeUtil.class);

    private static String errcode = null;

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
        }
        return openid;
    }

    public static NormalUser getUserInfoByCode(String code) throws Exception{
        String openid = null;
        String access_token = null;
        String refresh_token = null;
        NormalUser userInfo = new NormalUser();


        if (code == null || code.equals("")) throw new Exception("从前台获取的code为空");
        else {
            String url = AppConifg.get_openid_by_code_url;
            url = url.replace("APPID", AppConifg.appid);
            url = url.replace("CODE",code);
            url = url.replace("SECRET",AppConifg.secret);

            String responseStr = HttpClientUtil.doGet(url);
            JSONObject jsonObject = JSONObject.parseObject(responseStr);
            openid = jsonObject.getString("openid");
            access_token = jsonObject.getString("access_token");
            refresh_token = jsonObject.getString("refresh_token");

            if (!validationOfAccessToken(access_token,openid)){
                access_token = refreshAccessToken(refresh_token);
            }

            userInfo = getUserInfoByCorrectAccessToken(access_token,openid);
        }
        return userInfo;
    }

    /**
     * 通过正确的access_token获取用户信息
     * @param access_token 第一次获取到的或者刷新后的正确的access_token
     * @param openid openid
     * @return 用户信息
     */
    private static NormalUser getUserInfoByCorrectAccessToken(String access_token,String openid){
        NormalUser userInfo = new NormalUser();
        String url = AppConifg.get_unionid_by_code_url;
        url = url.replace("ACCESS_TOKEN",access_token);
        url = url.replace("OPENID",openid);

        String responseStr = HttpClientUtil.doGet(url);
        logger.info("responseStr: {}",responseStr);

        JSONObject jsonObject = JSONObject.parseObject(responseStr);

        String errorMsg,errorCode;
        errorCode = jsonObject.getString("errcode");
        errorMsg = jsonObject.getString("errmsg");
        if (errorCode!=null){
            logger.error("通过access_token获取客户信息失败：{}",errorMsg);
        }
        else {
            userInfo.setUnionid(jsonObject.getString("unionid"));
            userInfo.setOpenid(openid);
            userInfo.setNickName(jsonObject.getString("nickname"));
//            userInfo.setPrivilege(jsonObject.getString("privilege"));
            userInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            userInfo.setCity(jsonObject.getString("city"));
            userInfo.setCountry(jsonObject.getString("country"));
            userInfo.setProvince(jsonObject.getString("province"));
            userInfo.setSex(jsonObject.getString("sex"));

            logger.info("unionid: {}",userInfo.getUnionid());
            logger.info("nickname: {}",userInfo.getNickName());
            logger.info("city: {}",userInfo.getCity());
            logger.info("country: {}",userInfo.getCountry());
            logger.info("province: {}",userInfo.getProvince());
            logger.info("headimgurl: {}",userInfo.getHeadImgUrl());
            logger.info("sex: {}",userInfo.getSex());
        }

        return userInfo;
    }

    /**
     * 刷新access_token
     * @param refresh_token 第一次获取access_token时返回的参数
     * @return 新的正确的access_token
     */
    private static String refreshAccessToken(String refresh_token) {
        String url = AppConifg.get_refresh_access_token_url;
        url = url.replace("APPID",AppConifg.appid);
        url = url.replace("REFRESH_TOKEN",refresh_token);

        String responseStr = HttpClientUtil.doGet(url);
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        String accessToken = jsonObject.getString("access_token");
        String errMsg = jsonObject.getString("errmsg");
        String errCode = jsonObject.getString("errcode");

        if (errCode == null) return accessToken;
        else {
            logger.error("刷新access_token时错误：{}",errMsg);
            return null;
        }
    }

    /**
     * 验证access_token的有效性
     * @param access_token 第一次获取到的access_token
     * @param openid 第一次获取到的openid
     * @return 有效性
     */
    private static Boolean validationOfAccessToken(String access_token,String openid){
        String url = AppConifg.get_validation_of_access_token_url;
        url = url.replace("ACCESS_TOKEN",access_token);
        url = url.replace("OPENID",openid);

        String responseStr = HttpClientUtil.doGet(url);
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        String errCode = jsonObject.getString("errcode");
        String errMsg = jsonObject.getString("errmsg");

        if (errCode.equals("0")) return true;
        else {
            logger.info("accessToken无效：{}",errMsg);
            errcode = errCode;
            return false;
        }
    }


}
