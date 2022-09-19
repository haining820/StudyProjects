package com.haining820.futureapi;

import com.haining820.utils.SmallTool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA
 * Description: 测试allOf和anyOf
 * User: hn.yu
 * Date: 2022-07-22
 * Time: 15:07
 */
public class _08_allOfAnyOf {

    public void testAllOfAnyOf() throws ExecutionException, InterruptedException {

        // cf1会提前完成
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.sleepMillis(10);
            SmallTool.printTimeAndThread("cf1 working...");
            return "cf1 任务完成";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            SmallTool.sleepMillis(500);
            SmallTool.printTimeAndThread("cf2 working...");
            return "cf2 任务完成";
        });

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            SmallTool.sleepMillis(500);
            SmallTool.printTimeAndThread("cf3 working...");
//        int i = 1 / 0;
            return "cf3 任务完成";
        });

        System.out.println("===========cfAll===========");
        /**
         * 有异常就抛异常
         * 没异常get()会返回null
         */
        CompletableFuture<Void> cfAll = CompletableFuture.allOf(cf1, cf2, cf3);
        System.out.println("cfAll->" + cfAll.get());
        System.out.println("===========cfAny===========");
        /**
         * cf3会出现异常，但是cf1提前完成，anyOf会提前返回结果
         * 此时程序直接结束，不会抛出异常
         */
        CompletableFuture<Object> cfAny = CompletableFuture.anyOf(cf1, cf2, cf3);
        System.out.println("cfAny->" + cfAny.get());

    }
}