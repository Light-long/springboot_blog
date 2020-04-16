package com.tencent.aspect;


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

    //设置切面拦截web下的所有类
    @Pointcut("execution(* com.tencent.web.*.*(..))")
    public void log() {}


    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        //获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取url，ip
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //获取类名和方法名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() +"."+joinPoint.getSignature().getName();
        //获取请求参数
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);

        logger.info("RequestLog {}",requestLog);
    }

    @After("log()")
    public void doAfter() {
        logger.info("-------doAfter-------");
    }

    @AfterReturning(returning = "obj", pointcut = "log()")
    public void doAfterRunning(Object obj) {
        logger.info("Result: {}",obj);
    }


    private class RequestLog {

        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getClassMethod() {
            return classMethod;
        }

        public void setClassMethod(String classMethod) {
            this.classMethod = classMethod;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
