package com.example.demospringboot.utils;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    /**
     * 描述
     *
     * @return
     */
    String des() default "";
}
