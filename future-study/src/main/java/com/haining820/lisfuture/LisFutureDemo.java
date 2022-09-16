package com.haining820.lisfuture;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.haining820.CallableTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;


public class LisFutureDemo {

    public void lisFuture() throws ExecutionException, InterruptedException {
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> lf = pool.submit(new CallableTask());
        System.out.println(lf.get());
        lf.addListener(() -> {
            System.out.println("finish lf");
        }, Executors.newFixedThreadPool(5));
    }

}
