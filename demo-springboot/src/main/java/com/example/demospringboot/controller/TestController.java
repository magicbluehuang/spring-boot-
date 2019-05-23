package com.example.demospringboot.controller;

import com.example.demospringboot.model.Student;
import com.example.demospringboot.common.ResponseBody;
import com.example.demospringboot.utils.ResponseBodyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"TEST001 学生接口"})
public class TestController {

    @ApiOperation(value ="TEST001获取学生" ,httpMethod ="GET" )
    @GetMapping("/getStudent")
    public ResponseBody  getStudent(){
        return ResponseBodyUtil.success( new Student("huangjiangming"));
    }
}
