package com.example.demospringboot.utils;

import lombok.Data;

@Data
public class ResponseBody {
    private String cod;
    private String message;
    private Object data;
    private String desc;
}
