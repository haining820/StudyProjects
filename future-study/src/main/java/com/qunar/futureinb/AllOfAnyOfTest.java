package com.qunar.futureinb;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-22
 * Time: 15:07
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName AllOfAnyOfTest
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-22 15:07
 */
public class AllOfAnyOfTest {

    public static void testAllOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.sleepMillis(100);
            SmallTool.printTimeAndThread("cf1 working...");
            return "cf1 任务完成";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            SmallTool.sleepMillis(100);
            SmallTool.printTimeAndThread("cf2 working...");
            return "cf2 任务完成";
        });

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            SmallTool.sleepMillis(50);
            SmallTool.printTimeAndThread("cf3 working...");
//            int i = 1 / 0;
            return "cf3 任务完成";
        });

//        CompletableFuture<Void> cfAll = CompletableFuture.allOf(cf1, cf2, cf3);
//        System.out.println("cfAll->" + cfAll.get());
        CompletableFuture<Object> cfAny = CompletableFuture.anyOf(cf1, cf2, cf3);
        System.out.println("cfAll->" + cfAny.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testAllOf();
    }

}
