package com.haining820.service.impl;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-08
 * Time: 22:59
 */

import com.haining820.service.CompletableFutureAsyncService;
import com.haining820.utils.MyTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;


public class CompletableFutureAsyncServiceImpl implements CompletableFutureAsyncService {

    /**
     * 测试dubbo的异步实现方式：CompletableFuture（2.7）
     */

    private static Logger LOGGER = LoggerFactory.getLogger(CompletableFutureAsyncServiceImpl.class);

    @Override
    public CompletableFuture<String> sayHelloAsync(String name) {
        // 直接返回一个结果是name的CompletableFuture
        return CompletableFuture.supplyAsync(() -> {
            LOGGER.info("async provider received: " + name);
            MyTools.sleepMillis(5000);
            return "async response from provider.";
        });
    }
}
