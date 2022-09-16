package com.haining820.state;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-03
 * Time: 19:47
 */
public class TestJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程vip来了，快跑！-->" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();
        // 主线程
        for (int j = 0; j < 500; j++) {
            if (j == 20) {
                thread.join();  // vip线程插队进去后要跑完，强制执行vip线程
            }
            System.out.println("main-->" + j);
        }
    }
}
