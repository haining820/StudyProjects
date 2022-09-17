package com.haining820.timeoutfuture;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeoutFutureTest {


    @Test
    public void testComFuture1() throws ExecutionException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        TimeoutFutureProcess comFuture = (TimeoutFutureProcess) context.getBean("comFuture");
        comFuture.process();
    }

    @Test
    public void testGet() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        Integer integer = future.get(1, TimeUnit.SECONDS);

        System.out.println(integer);
    }

}
