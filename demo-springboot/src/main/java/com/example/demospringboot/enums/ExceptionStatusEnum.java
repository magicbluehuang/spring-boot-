package com.example.demospringboot.enums;

/**
 * 状态码
 */
public enum ExceptionStatusEnum {
    INDEX_OUTOF("000","角标越界"),
    SYSTEM_ERRO("999","系统异常"),
    ;

    ExceptionStatusEnum(String code, String message){
        this.code=code;
        this.message=message;
    }

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}