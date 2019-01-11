package com.duobi.wuye.filter;

import com.duobi.wuye.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IpInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(IpInterceptor.class);

//    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //只有返回true才会继续向下执行，返回false取消当前请求
        String ipAddress= IPUtils.getRealIP(httpServletRequest);

        logger.info("用户的ip地址是：{}",ipAddress);
        return true;
    }

}
