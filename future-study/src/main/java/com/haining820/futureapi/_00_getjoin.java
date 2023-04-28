package com.haining820.futureapi;

import com.haining820.utils.SmallTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
@Slf4j
public class _00_getjoin {

    @Test
    public void getTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        Integer integer = future.get();
        // future中没有内容，会一直阻塞，不会输出结果
        log.info("1");
    }

    @Test
    public void timeoutGetTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        try {
            // 超过规定的5s就会抛出超时异常
            Integer integer = future.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        log.info("3");
    }

    @Test
    public void getNowTest() {
        CompletableFuture<Integer> cf1 = new CompletableFuture<>();
        // cf没有结果，打印 666
        log.info("cf getNow#res={}", cf1.getNow(666));
        CompletableFuture<Integer> cf2 = new CompletableFuture<>();
        cf2.complete(777);
        // cf有结果，直接输出结果，打印 777
        log.info("cf get#res={}", cf2.getNow(666));
    }

    @Test
    public void getNowTest2() {
        CompletableFuture<Integer> getNowCf = CompletableFuture.supplyAsync(() -> sleepMillis(1000));
        // sleepMillis操作需要等待，没有结果立即返回，打印 666
        log.info("cf getNow#res={}", getNowCf.getNow(666));
        CompletableFuture<Integer> getCf = CompletableFuture.supplyAsync(() -> sleepMillis(1000));
        try {
            // 等待sleepMillis结果完成，打印 1
            log.info("cf get#res={}", getCf.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

}
