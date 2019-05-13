package com.example.demospringboot.controller;

import com.example.demospringboot.Exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class DemoTest {

    @RequestMapping("/test")
    public ModelAndView test(ModelAndView model){
        String [] bb = new String[1];
        try {
            System.out.println(bb[2]);
        }catch (Exception e){
            throw  new MyException("角标越界", "0000",e);
        }
        model.setViewName("test.html");
        log.info("success");
        log.error("error");
        return model;
    }
}
