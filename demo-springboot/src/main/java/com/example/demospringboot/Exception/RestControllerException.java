package com.example.demospringboot.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


/**
 * 异常处理类
 * 返回json格式
 *
 */
@ControllerAdvice
@RestController
@Slf4j
public class RestControllerException {

    @ExceptionHandler(value=MyException.class)
    public Map<String, String> responseException(MyException me) {
        log.error(me.getMessage(), me);
        HashMap<String, String> resultException = new HashMap<>();
        resultException.put("code", me.getCode());
        resultException.put("message", me.getMessage());
        resultException.put("desc", me.getCause()==null ? "": me.getCause().getClass()+":"+me.getCause().getMessage());
        return resultException;
    }

    @ExceptionHandler(value = Exception.class)
    public Map<String, String> responseCommonException(Exception me) {
        log.error(me.getMessage(), me);
        HashMap<String, String> resultException = new HashMap<>();
        resultException.put("code", "500");
        resultException.put("message", me.getMessage());
        return resultException;
    }
}
