package com.qunar.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 1、程序运行结果的输出顺序是什么？发短信 打电话
 * 2、sendMessage()延迟4秒运行，程序运行结果的输出顺序是什么？发短信 打电话
 *  发短信拿到了锁没有释放
 *  synchronized锁的对象是方法的调用者！
 *  Main中两个方法用的是同一个锁，一个对象只有一个锁，谁先拿到谁就执行！
 */

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendMessage();    // 先调用不是先执行，有锁，先拿到锁就会阻塞
        },"A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            phone.call();
        },"B").start();
    }
}

class Phone {
    public synchronized void sendMessage() {
        /*try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
}
