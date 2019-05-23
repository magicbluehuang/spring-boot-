package com.example.demospringboot.utils;

import com.example.demospringboot.Exception.MyException;
import com.example.demospringboot.common.ResponseBody;

public class ResponseBodyUtil {

    public static ResponseBody success(Object data) {
        ResponseBody rb = new ResponseBody();
        rb.setCode("000");
        rb.setMessage("成功");
        rb.setData(data);
        return rb;
    }

    public static ResponseBody exeption(MyException e){
        ResponseBody rb = new ResponseBody();
        rb.setCode(e.getCode());
        rb.setMessage(e.getMessage());
        rb.setDesc(e.getCause()==null ? "": e.getCause().getClass()+":"+e.getCause().getMessage());
        return  rb;
    }
}
