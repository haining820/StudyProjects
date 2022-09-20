package com.haining820.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 测试：
 * 7、1个对象，1个静态同步方法，1个普通同步方法
 * 一个锁的是class类模板，一个锁的是调用者，锁的对象不一样，打印顺序取决于时间延迟
 *
 * 8、2个对象，1个静态同步方法，1个普通同步方法
 * 锁的对象仍然不一样，结果仍然不变
 */
public class _4_1staticmethod1method {

    /**
     * 1个对象，1个静态同步方法sendMessage，1个普通同步方法call
     * 打电话 发短信
     */
    public static void method1() throws InterruptedException {
        StaticSynPhone phone = new StaticSynPhone();
        new Thread(() -> {
            phone.sendMessage();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            phone.call();
        }, "B").start();
    }

    /**
     * 2个对象，1个静态同步方法sendMessage，1个普通同步方法call
     * 打电话 发短信
     */
    public static void method2() throws InterruptedException {
        StaticSynPhone phone1 = new StaticSynPhone();
        StaticSynPhone phone2 = new StaticSynPhone();
        new Thread(() -> {
            phone1.sendMessage();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }

    public static void main(String[] args) throws InterruptedException {
        method1();
    }
}

class StaticSynPhone {

    public static synchronized void sendMessage() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }

}
