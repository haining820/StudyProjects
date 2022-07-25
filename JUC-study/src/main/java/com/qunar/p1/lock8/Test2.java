package com.qunar.p1.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 1、增加一个普通方法hello，程序运行结果的输出顺序是什么？hello 发短信
 * 普通方法这里没有锁！不是同步方法，不受锁的影响
 * 2、两个对象，两个同步方法，程序运行结果的输出顺序是什么？打电话 发短信（因为有延迟）
 * 有两个调用者，两个不同的对象，是两把不同的锁！结果就是按时间顺序来的
 * phone2打电话不需要延迟，phone1发短信会有延迟，所以结果是打电话 发短信
 */

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        HelloPhone phone1 = new HelloPhone();
        HelloPhone phone2 = new HelloPhone();   // 2

        new Thread(()->{
            phone1.sendMessage();
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            // phone1.hello();     // 2
            phone2.call();
        },"B").start();
    }
}

class HelloPhone {
    // synchronized锁的对象是方法的调用者！
    // Main中两个方法用的是同一个锁，谁先拿到谁就执行！
    public synchronized void sendMessage() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
    // 这里没有锁！不是同步方法，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}
