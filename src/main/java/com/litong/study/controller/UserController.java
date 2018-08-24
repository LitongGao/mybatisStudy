package com.litong.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/litong/")
public class UserController {
    @Value("${name}")
    private String name;
    @Value("${age}")
    private Integer age;
    @Value("${content}")
    private String content;

    @RequestMapping(value = "getuser", method = RequestMethod.GET)
    public String getUser() {
        return "The user is " + name + " and the age is "+age +"===" +content;
    }
}
