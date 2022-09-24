package com.haining820.springbootstudy.controller;

import com.haining820.springbootstudy.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-29
 * Time: 10:13
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello,SpringBoot!";
    }

    @Autowired
    Student student;

    @RequestMapping("/student")
    public String helloStudent() {
        return student.toString();
    }

}
