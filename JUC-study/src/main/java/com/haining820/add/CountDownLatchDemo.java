package com.haining820.add;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 当有必须要执行的任务的时候使用CountDownLatch，这里是6个
        CountDownLatch countDownLatch = new CountDownLatch(6);   // 总数是6
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"go out!");
                countDownLatch.countDown();  // 数量-1
            },String.valueOf(i)).start();
        }
        countDownLatch.await();  // 等待计数器归0，然后再向下执行
        System.out.println("close door!");
    }
}
