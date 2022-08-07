package com.haining820;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ProviderMainApplication {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("service-provider.xml");
        ioc.start();
        // 阻塞，读取一个字符（按任意键退出）
        System.in.read();
    }
}
