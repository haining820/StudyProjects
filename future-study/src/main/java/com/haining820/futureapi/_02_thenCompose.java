package com.haining820.futureapi;

import com.haining820.utils.SmallTool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 * Description: 测试ThenCompose和ThenComposeAsync
 * User: hn.yu
 * Date: 2022-07-21
 * Time: 10:18
 */

public class _02_thenCompose {

    /**
     * 创建一个keepAliveTime为0的线程池，线程处理完任务处于空闲状态时，存活时间为0，立即消失
     */
    public ExecutorService verifyPool = new ThreadPoolExecutor(1, Integer.MAX_VALUE,
            0, TimeUnit.MILLISECONDS,
            new SynchronousQueue<>());

    public void thenComposeTest() {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(200);
            return "番茄炒蛋";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员打饭");
            SmallTool.sleepMillis(100);
            return dish + " + 米饭";
        }));
        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s 好了,小白开吃", cf.join()));
    }

    public void thenComposeAsyncTest() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(200);
            return "番茄炒蛋";
        }).thenComposeAsync(dish -> {
            SmallTool.printTimeAndThread("服务员A 准备打饭，但是被领导叫走，打饭交接给服务员B");

            return CompletableFuture.supplyAsync(() -> {
                SmallTool.printTimeAndThread("服务员B 打饭");
                SmallTool.sleepMillis(100);
                return dish + " + 米饭";
            });
        });
        SmallTool.printTimeAndThread(cf.join() + "好了，开饭");
    }
}
