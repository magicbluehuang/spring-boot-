package com.example.demospringboot.utils;

public class ResponseBodyUtil {

    public static ResponseBody success(Object data) {
        ResponseBody rb = new ResponseBody();
        rb.setCod("000");
        rb.setMessage("成功");
        rb.setData(data);
        return rb;
    }

    public static ResponseBody error(){
        ResponseBody rb = new ResponseBody();
        rb.setCod("999");
        rb.setMessage("失败");
        return  rb;
    }
}
