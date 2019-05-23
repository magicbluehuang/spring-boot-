package com.example.demospringboot.aspect;

import com.alibaba.fastjson.JSON;
import com.example.demospringboot.Exception.MyException;
import com.example.demospringboot.enums.ExceptionStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * reated by Administrator on 2018/11/5.C
 * springboot+aop切点记录请求和响应信息
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    //切点入口 Controller包下面所有类的所有方法
    private final String pointcut = "execution(* com.example.demospringboot.controller..*(..))";

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //切点
    @Pointcut(value = pointcut)
    public void log() {
    }

    @Around(value = "log()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取HttpServletRequest对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        获取HttpServletResponse对象
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        log.info("************************************************************************");
        log.info("请求接口："+request.getRequestURL().toString());
        try {
            log.info(String.format("类名：%s", proceedingJoinPoint.getTarget().getClass().getName()));
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            log.info(String.format("方法：%s", methodSignature.getMethod().getName()));

            Object[] args = proceedingJoinPoint.getArgs();
            for (Object o : args) {
                log.info(String.format("参数：%s", JSON.toJSON(o)));
            }

            long startTime = System.currentTimeMillis();
            long endTime = System.currentTimeMillis();
            log.info(String.format("返回：%s", JSON.toJSON( proceedingJoinPoint.proceed())));
            log.info(String.format("耗时：%ss", endTime - startTime));
            log.info("************************************************************************");
        } catch (Exception ex) {
            throw  new MyException(ExceptionStatusEnum.SYSTEM_ERRO,ex);
        }
        return proceedingJoinPoint.proceed();
    }
}