package com.haining820.futureapi;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-19
 * Time: 17:34
 */
public class _00_getjoin {

    public void getTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        Integer integer = future.get();
        int i = 3;
        System.out.println(i);  //  future中没有内容，会一直阻塞，不会输出结果
    }

    public void timeoutGetTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        try {
            Integer integer = future.get(5, TimeUnit.SECONDS);  // 超过规定的5s就会抛出超时异常
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        int i = 3;
        System.out.println(i);
    }

    public void getNowTest() {
        CompletableFuture<Integer> cf = new CompletableFuture<>();
        Integer i = cf.getNow(666);
        System.out.println("cf->" + i);    // cf没有结果，输出默认结果666

        CompletableFuture<Integer> cf2 = new CompletableFuture<>();
        cf2.complete(777);
        Integer i2 = cf2.getNow(666);
        System.out.println("cf2->"+i2);    // cf有结果，直接输出结果，输出777
    }

}
