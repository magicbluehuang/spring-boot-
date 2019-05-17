package com.example.demospringboot.mode;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Student {

    @NotNull(message="名字不能为空")
    @Size(min = 5, max = 20, message = "用户名个数必须为5-20位")
    private String name;
}
