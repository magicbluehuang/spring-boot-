package com.example.demospringboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @NotEmpty(message="名字不能为空")
    @Size(min = 5, max = 20, message = "用户名个数必须为5-20位")
    private String name;

}
