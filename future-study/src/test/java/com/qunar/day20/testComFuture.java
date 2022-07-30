package com.qunar.day20;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-20
 * Time: 13:43
 */
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Test
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-20 13:43
 */
public class testComFuture {


    @Test
    public void testComFuture1() throws ExecutionException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ComFuture comFuture = (ComFuture) context.getBean("comFuture");
        comFuture.testComFuture();
    }

    @Test
    public void testGet() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        Integer integer = future.get(1, TimeUnit.SECONDS);


        System.out.println(integer);
    }

}
