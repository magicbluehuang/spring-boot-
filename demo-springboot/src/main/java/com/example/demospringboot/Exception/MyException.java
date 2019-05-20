package com.example.demospringboot.Exception;

import com.example.demospringboot.enums.ExceptionStatusEnum;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class MyException extends RuntimeException{
    private  String code;
    private  String message;

    public MyException(ExceptionStatusEnum es, Throwable tb) {
        super(tb.getMessage(),tb);
        this.code = es.getCode();
        this.message = es.getMessage();
    }
}
