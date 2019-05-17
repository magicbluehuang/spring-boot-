package com.example.demospringboot.controller;

import com.example.demospringboot.mode.Student;
import com.example.demospringboot.utils.ResponseBody;
import com.example.demospringboot.utils.ResponseBodyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "DEMO001 分类测试")
@RestController
public class DemoTest {

    @PostMapping("/test")
    @ApiOperation(value = "DEMO01 客户首页查询", httpMethod = "POST")
    public ResponseBody test(@RequestBody @Validated Student student ){
//        String [] bb = new String[1];
//        try {
//            System.out.println(bb[2]);
//        }catch (Exception e){
//            throw  new MyException(ExceptionStatusEnum.INDEX_OUTOF,e);
//        }
        return ResponseBodyUtil.success(student);
    }
}
