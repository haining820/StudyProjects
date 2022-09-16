package com.haining820.p1;


import org.apache.log4j.Logger;

import java.util.concurrent.CompletableFuture;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-19
 * Time: 14:37
 */
public class CompletableFutureDemo {

    private static Logger logger = Logger.getLogger(CompletableFutureDemo.class);
    static Double getPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("Error when get price!");
        }
        return Math.random() * 20;
    }

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(CompletableFutureDemo::getPrice)
                .thenAccept(result->{
                    logger.info("price:"+result);
                })
                .exceptionally(e->{
                    e.printStackTrace();
                    return null;
                });
        Thread.sleep(100);
    }

}
