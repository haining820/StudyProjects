package com.haining820.lisfuture;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.haining820.CallableTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;


/**
 * 使用addListener添加回调函数
 */

@Slf4j
public class AddListenerTest{

    public static void addLis() throws ExecutionException, InterruptedException {
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> lf = pool.submit(new CallableTask());
        log.info(lf.get());
        lf.addListener(() -> {
            log.info("finish lf");
        }, Executors.newFixedThreadPool(5));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        addLis();
    }

}
