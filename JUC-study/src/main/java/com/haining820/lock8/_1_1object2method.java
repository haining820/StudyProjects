package com.haining820.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 测试：
 * 明确：synchronized锁的对象是方法的调用者
 *
 * 1、1个对象，2个同步方法
 * 发短信拿到了锁没有释放
 * main方法中两个方法用的是同一个锁，一个对象只有一个锁，谁先拿到谁就执行
 *
 * 2、sendMessage()延迟4秒运行，程序运行结果的输出顺序是什么？
 * 第一个方法先拿到了锁没有释放，等待4秒后释放，然后第二个方法执行，顺序不改变
 */

public class _1_1object2method {

    /**
     * 1个对象，2个同步方法
     * 输出顺序：打电话 发短信（因为有延迟）
     */
    public static void main(String[] args) throws InterruptedException {
        // main中两个方法用的是同一个锁，谁先拿到谁就执行
        // 先调用不是先执行，有锁，先拿到锁就会阻塞
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendMessage();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

class Phone {

    public synchronized void sendMessage() {
        // 添加延时后顺序依然不变
        /*try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }
}
