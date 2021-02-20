package com.example.blog.interceptor;

import com.example.blog.annotation.AutoIdempotent;
import com.example.blog.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class BlogIdempotentInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    /**
     * 预处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //被ApiIdempotment标记的扫描
        AutoIdempotent methodAnnotation = method.getAnnotation(AutoIdempotent.class);
        if (methodAnnotation != null) {
            if (tokenService.checkToken(request)) {
                log.info("请求处理成功");
                return true;
            } else {
                log.info("请勿重复提交表单");
                response.sendRedirect("/admin/blogs");
                return false;
            }
        }
        //必须返回true,否则会被拦截一切请求
        return true;
    }

}
