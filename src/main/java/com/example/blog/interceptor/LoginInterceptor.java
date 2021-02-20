package com.example.blog.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {//参数中的handler为此次请求的url对应的一个handler，如果
            return true;
        }
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
            return false;//如果没有权限，则被拦截
        }
        return true;
    }
}
