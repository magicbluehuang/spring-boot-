package com.example.demospringboot.common;

import lombok.Data;

@Data
public class ResponseBody {
    private String code;
    private String message;
    private Object data;
    private String desc;
}
