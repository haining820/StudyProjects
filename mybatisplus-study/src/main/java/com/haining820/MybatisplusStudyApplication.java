package com.haining820;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 使用MapperScan注解扫描Mapper文件夹
//@MapperScan("com.haining820.mapper")  // 建立mybatisplus配置类之后就转移到配置类中
@SpringBootApplication
public class MybatisplusStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusStudyApplication.class, args);
    }

}
