package com.haining820.lock8;

import java.util.concurrent.TimeUnit;


/**
 * 5、增加两个静态的同步方法,只有一个对象，打印顺序是怎样的？发短信 打电话
 * static 静态方法，类一加载就有了，锁的对象是class对象，Phone3.class
 * 两个方法用的是同一个锁
 * 6、增加两个静态同步方法，使用两个对象，打印的顺序是怎样的？发短信 打电话
 * 锁的是class对象，两个对象的Class类模板只有一个，static，所以顺序不变
 */
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        StaticPhone phone1 = new StaticPhone();
        StaticPhone phone2 = new StaticPhone();
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

class StaticPhone {
    public static synchronized void sendMessage() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public static synchronized void call(){
        System.out.println("打电话");
    }
}
