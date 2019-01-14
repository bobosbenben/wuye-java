package com.duobi.wuye.utils.weixinutils;

public class AppConifg {

    public static String appid = "wx62865bf34b25ba13";

    public static String secret = "6760a371cdc20196132c04e3c3d03cf9";

    public static String get_openid_by_code_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    public static String get_unionid_by_code_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    public static String get_refresh_access_token_url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

    public static String get_validation_of_access_token_url = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";

    public static String mch_id = "1502077531";

    public static String appkey = "11082611611shiyibo11082611611shi";

    public static String get_pre_pay_id_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

}
