package com.haining820.state;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-22
 * Time: 18:07
 */
public class StateCheck {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("////////////");
        });
        Thread.State state = thread.getState();
        // 观察线程起始状态
        System.out.println("1->" + state);
        // 线程启动
        thread.start();
        // 观察线程启动后的状态
        state = thread.getState();
        System.out.println("2->" + state);

        thread.interrupt();
        System.out.println("3->" + state);
        // 只要线程不终止，就一直输出状态
        while (state != Thread.State.TERMINATED) {
            Thread.sleep(100);
            // 更新线程状态
            state = thread.getState();
            System.out.println("4->" + state);
        }


    }
}
