package com.haining820.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 测试：
 * 3、2个对象，2个同步方法
 * 两个对象，两个同步方法，程序运行结果的输出顺序是什么？
 * 有两个调用者，两个不同的对象，是两把不同的锁，调用各自独立，结果就是按时间顺序来的
 * phone2打电话不需要延迟，phone1发短信会有延迟，所以结果是打电话 发短信
 *
 * 4、1个对象，1个同步方法，1个普通方法
 * 普通方法没有锁，不是同步方法，不受锁的影响，会直接输出
 * 而同步方法sendMessage中含有延时操作，会较晚输出
 */

public class _2_2object2method {

    /**
     * 2个对象，2个同步方法
     * 输出顺序：打电话 发短信（因为有延迟）
     */
    public static void method1() throws InterruptedException {
        HelloPhone phone1 = new HelloPhone();
        HelloPhone phone2 = new HelloPhone();

        new Thread(()->{
            phone1.sendMessage();
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            phone2.call();
        },"B").start();
    }

    /**
     * 1个对象，1个同步方法sendMessage，1个普通方法hello
     * 输出顺序：hello 发短信
     */
    public static void method2() throws InterruptedException {
        HelloPhone phone1 = new HelloPhone();

        new Thread(()->{
            phone1.sendMessage();
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
             phone1.hello();
        },"B").start();
    }

    public static void main(String[] args) throws InterruptedException {
        method2();
    }
}

class HelloPhone {
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
    // 这里没有锁，不是同步方法，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}
