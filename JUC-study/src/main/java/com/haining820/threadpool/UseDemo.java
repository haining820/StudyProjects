package com.haining820.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-18
 * Time: 15:43
 */


public class UseDemo {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();     // 单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);   // 创建一个固定大小的线程池
//        ExecutorService threadPool2 = Executors.newCachedThreadPool();   // 大小可伸缩的线程池

        // 获取CPU的核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        // 自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        // 四种拒绝策略
        // ThreadPoolExecutor.AbortPolicy()         // 银行满了，还有人进来，不处理这个人，抛出异常
        // ThreadPoolExecutor.CallerRunsPolicy()    // 哪来的去哪里
        // ThreadPoolExecutor.DiscardPolicy()       // 队列满了，丢掉任务，不会抛出异常
        // ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试去和最早的线程竞争，竞争失败了就会丢掉，也不会抛出异常
        try {
            // 最大承载量：queue+max
            for (int i = 1; i <= 9; i++) {
                // 使用线程池创建线程
                int finalI = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " OK-"+ finalI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 程序结束，线程池用完，要关闭
            threadPool.shutdown();
        }
    }
}

