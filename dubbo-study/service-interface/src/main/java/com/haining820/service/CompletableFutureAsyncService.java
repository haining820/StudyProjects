package com.haining820.service;

import java.util.concurrent.CompletableFuture;

/**
 * Created with IntelliJ IDEA
 * Description: 测试dubbo的异步实现方式：CompletableFuture（2.7）
 * User: hn.yu
 * Date: 2022-08-08
 * Time: 22:43
 */
public interface CompletableFutureAsyncService {

    CompletableFuture<String> sayHelloAsync(String name);

}