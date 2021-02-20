package com.example.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.controller.*.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String classMethod =
                joinPoint.getSignature().getDeclaringTypeName() + "." +
                        joinPoint.getSignature().getName();
        RequestLog reqeustLog = new RequestLog(
                request.getRequestURL().toString(),
                request.getRemoteAddr(),
                classMethod,
                joinPoint.getArgs()
        );
        logger.info("Rquest ----- {}", reqeustLog);
    }

    @After("log()")
    public void doAfter() {
        logger.info("-----------------doafter---------------");
    }

    //记录方法返回之后的内容
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("Result  : {}", result);
    }

    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}