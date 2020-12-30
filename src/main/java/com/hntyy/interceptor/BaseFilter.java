package com.hntyy.interceptor;

import com.hntyy.interceptor.GetHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 */
@WebFilter(filterName = "baseFilter",urlPatterns = "/*")
public class BaseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       // System.out.println("testFilter init......");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //System.out.println("testFilter filter start ......");
        servletRequest = new GetHttpServletRequestWrapper((HttpServletRequest) servletRequest);
     /*  Enumeration<String> hs = ((GetHttpServletRequestWrapper) servletRequest).getHeaderNames();
        System.out.println("accountId="+servletRequest.getParameter("accountId"));
       while(hs.hasMoreElements()){
           String hv = hs.nextElement();
           System.out.println("请求header参数键："+hv);
           System.out.println("请求header参数值："+((GetHttpServletRequestWrapper) servletRequest).getHeader(hv));
       }*/
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        //System.out.println("testFilter filter end ......");
    }
}