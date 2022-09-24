package com.haining820.state;

import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-22
 * Time: 17:47
 */
@Slf4j
public class SleepDemo {

    public void sleepForaWhile() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        log.info(thread.getState().toString());
        thread.start();
        log.info(thread.getState().toString());

    }

}
