package com.example.demospringboot.utils;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/11/5.
 * springboot+aop切点记录请求和响应信息
 */
@Component
@Aspect
public class LogAspect {

    //切点入口 Controller包下面所有类的所有方法
    private final String pointcut = "execution(* com.example.demospringboot.controller..*(..))";

    //匹配方法上包含此注解的方法
    private final String annotationPointCut = "@annotation(com.example.demospringboot.utils.LogAnnotation)";

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //切点
    @Pointcut(value = pointcut)
    public void log() {
    }

    @Around(value = "log()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        StringBuilder sbLog = new StringBuilder("\n");
        try {
            sbLog.append(String.format("类名：%s\r\n", proceedingJoinPoint.getTarget().getClass().getName()));

            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            sbLog.append(String.format("方法：%s\r\n", methodSignature.getMethod().getName()));

            Object[] args = proceedingJoinPoint.getArgs();
            for (Object o : args) {
                sbLog.append(String.format("参数：%s\r\n", JSON.toJSON(o)));
            }

            long startTime = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long endTime = System.currentTimeMillis();
            sbLog.append(String.format("返回：%s\r\n", JSON.toJSON(result)));
            sbLog.append(String.format("耗时：%ss", endTime - startTime));
        } catch (Exception ex) {
            sbLog.append(String.format("异常：%s", ex.getMessage()));
        } finally {
            logger.info(sbLog.toString());
        }
        return result;
    }

    //注解切点
    @Pointcut(value = annotationPointCut)
    public void logAnnotation() {
    }

    @Around(value = "logAnnotation()")
    public Object aroundAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        StringBuilder sbLog = new StringBuilder("\n");
        try {
            sbLog.append(String.format("类名：%s\r\n", proceedingJoinPoint.getTarget().getClass().getName()));

            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = methodSignature.getMethod();
            LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
            if (logAnnotation != null && !logAnnotation.des().isEmpty()) {
                sbLog.append(String.format("说明：%s\r\n", logAnnotation.des()));
            }
            sbLog.append(String.format("方法：%s\r\n", method.getName()));

            Object[] args = proceedingJoinPoint.getArgs();
            for (Object o : args) {
                sbLog.append(String.format("参数：%s\r\n", JSON.toJSON(o)));
            }

            long startTime = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long endTime = System.currentTimeMillis();
            sbLog.append(String.format("返回：%s\r\n", JSON.toJSON(result)));
            sbLog.append(String.format("耗时：%ss", endTime - startTime));
        } catch (Exception ex) {
            sbLog.append(String.format("异常：%s", ex.getMessage()));
        } finally {
            logger.info(sbLog.toString());
        }
        return result;
    }
}