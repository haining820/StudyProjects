package com.haining820.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    // 集齐七颗龙珠召唤神龙
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功！");
        });
        for (int i = 1; i <= 7; i++) {
            int temp = i; // lambda操作不到i
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集到" + temp + "星球");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
