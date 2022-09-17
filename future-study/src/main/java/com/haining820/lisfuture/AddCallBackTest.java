package com.haining820.lisfuture;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.haining820.CallableTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;


/**
 * 使用addCallback为任务绑定回调接口
 */

@Slf4j
public class AddCallBackTest {

    public static void addCB() {
        log.info("主任务执行完,开始异步执行副任务1.....");
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> future = pool.submit(new CallableTask());
        // 为任务绑定回调接口
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                log.info("success,result->:" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                log.info("error->" + t);
            }
        });
        log.info("副本任务启动,回归主任务线,主业务正常返回.....");
    }

    public static void main(String[] args) {
        addCB();
    }
}