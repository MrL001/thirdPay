package com.hntyy.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 参数拦截器
 */
@Component
public class ParamsInterceptor implements HandlerInterceptor {
    Logger logger=	LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Map ParameterMap =  httpServletRequest.getParameterMap();
        Map reqMap = new HashMap();
        Set<Map.Entry<String,String[]>> entry = ParameterMap.entrySet();
        Iterator<Map.Entry<String,String[]>> it = entry.iterator();
        while (it.hasNext()){
            Map.Entry<String,String[]>  me = it.next();
            String key = me.getKey();
            String value = me.getValue()[0];
            reqMap.put(key,value);
        }
       /* this.logger.info("请求参数："+JSONObject.fromObject(reqMap));
        Enumeration<String> hs = httpServletRequest.getHeaderNames();
       while(hs.hasMoreElements()){
           String hv = hs.nextElement();
           this.logger.info("请求header参数键："+hv);
           this.logger.info("请求header参数值："+httpServletRequest.getHeader(hv));
       }*/
       // System.out.println(JSONObject.fromObject(reqMap));
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }
    
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}