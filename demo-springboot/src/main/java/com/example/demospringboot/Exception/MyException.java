package com.example.demospringboot.Exception;

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

    public MyException(String message, String code,Throwable tb) {
        super(tb.getMessage(),tb);
        this.code = code;
        this.message = message;
    }
}
