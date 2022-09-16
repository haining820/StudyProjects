package com.haining820.timeoutfuture;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class testComFuture {


    @Test
    public void testComFuture1() throws ExecutionException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        CompletableFutureTest comFuture = (CompletableFutureTest) context.getBean("comFuture");
        comFuture.testComFuture();
    }

    @Test
    public void testGet() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        Integer integer = future.get(1, TimeUnit.SECONDS);


        System.out.println(integer);
    }

}
