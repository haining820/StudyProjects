package com.haining820.lock8;

import java.util.concurrent.TimeUnit;


/**
 * 测试：
 * 5、1个对象，2个静态同步方法
 * static静态方法，类一加载就有了，锁的对象是class对象，StaticPhone.class
 * 两个方法用的是同一个锁
 *
 * 6、2个对象，2个静态同步方法
 * 锁的是class对象，两个对象的class类模板只有一个，无论有几个对象顺序都不变
 */
public class _3_2staticmethod {

    /**
     * 1个对象，2个静态同步方法
     * 打印顺序：发短信 打电话
     */
    public static void method1() throws InterruptedException {
        StaticPhone phone = new StaticPhone();
        new Thread(() -> {
            phone.sendMessage();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            phone.call();
        }, "B").start();
    }

    /**
     * 2个对象，2个静态同步方法
     * 打印顺序：发短信 打电话
     */
    public static void method2() throws InterruptedException {
        StaticPhone phone1 = new StaticPhone();
        StaticPhone phone2 = new StaticPhone();
        new Thread(() -> {
            phone1.sendMessage();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }

    public static void main(String[] args) throws InterruptedException {
        method2();
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

    public static synchronized void call() {
        System.out.println("打电话");
    }
}
