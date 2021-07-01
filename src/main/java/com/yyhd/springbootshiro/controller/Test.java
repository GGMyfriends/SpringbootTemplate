package com.yyhd.springbootshiro.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {

    @PostMapping("test1")
    public String test() {
        System.out.println("123123123");
        return "123123123";
    }

}
