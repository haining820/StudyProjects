package com.haining820;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.haining820.mapper")

@SpringBootApplication
public class MybatisplusStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusStudyApplication.class, args);
    }

}
