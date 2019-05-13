package com.example.demospringboot.Exception;

import com.example.demospringboot.enums.ExceptionStatusEnum;

/**
 * 自定义异常
 */
public class MyException extends RuntimeException{
    private  String code;
    private  String message;

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public MyException(ExceptionStatusEnum es, Throwable tb) {
        super(tb.getMessage(),tb);
        this.code = es.getCode();
        this.message = es.getMessage();
    }
}
