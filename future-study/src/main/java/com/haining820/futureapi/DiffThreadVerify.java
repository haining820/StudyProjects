package com.haining820.futureapi;

import com.haining820.utils.SmallTool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 * Description: 验证两个异步任务在不同的线程中被执行
 * User: hn.yu
 * Date: 2022-09-18
 * Time: 17:06
 */
public class DiffThreadVerify {

    public static void withoutVerify() {
        CompletableFuture.runAsync(() -> SmallTool.printTimeAndThread("A"))
                .thenRunAsync(() -> SmallTool.printTimeAndThread("B"))
                .join();
    }

    public static void withVerify() {
        /**
         * 创建一个keepAliveTime为0的线程池，线程处理完任务处于空闲状态时，存活时间为0，立即消失
         * 以下的A、B操作必不可能在同一个线程中被执行
         */
        ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                0, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>());
        CompletableFuture.runAsync(() -> SmallTool.printTimeAndThread("A"), executor)
                .thenRunAsync(() -> SmallTool.printTimeAndThread("B"), executor)
                .join();
    }

    public static void main(String[] args) {
        withoutVerify();
        withVerify();
    }

}
