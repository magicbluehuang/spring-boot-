package com.example.demospringboot.controller;

import com.example.demospringboot.Exception.MyException;
import com.example.demospringboot.enums.ExceptionStatusEnum;
import com.example.demospringboot.utils.ResponseBody;
import com.example.demospringboot.utils.ResponseBodyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class DemoTest {

    @GetMapping("/test")
    public ResponseBody test(ModelAndView model){
        String [] bb = new String[1];
        try {
            System.out.println(bb[2]);
        }catch (Exception e){
            throw  new MyException(ExceptionStatusEnum.INDEX_OUTOF,e);
        }
        model.setViewName("test.html");
        log.info("success");
        log.error("error");
        return ResponseBodyUtil.success("success");
    }
}
