package com.qunar.p1.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 1个静态同步方法，1个普通同步方法，1个对象，打印的顺序是怎样的？打电话 发短信
 * 一个锁的是class类模板，一个锁的是调用者，锁的对象时不一样的，有时间延迟
 * 1个静态同步方法，1个普通同步方法，2个对象，打印的顺序是怎样的？打电话 发短信
 * 锁的对象仍然不一样，结果仍然不变
 */
public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        // StaSynPhone phone = new StaSynPhone();
        StaSynPhone phone1 = new StaSynPhone();
        StaSynPhone phone2 = new StaSynPhone();
        new Thread(()->{
            // phone.sendMessage();
            phone1.sendMessage();
        },"A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            // phone.call();
            phone2.call();
        },"B").start();
    }
}

class StaSynPhone {
    public static synchronized void sendMessage() {
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

}
