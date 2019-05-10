package com.example.demospringboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class DemoTest {

    @RequestMapping("/test")
    public ModelAndView test(ModelAndView model){
        model.setViewName("test.html");
        log.info("success");
        log.error("error");
        return model;
    }
}
