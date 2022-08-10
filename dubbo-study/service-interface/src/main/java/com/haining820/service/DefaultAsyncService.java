package com.haining820.service;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-10
 * Time: 9:57
 */

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName DefaultAsyncService
 * @Description TODO
 */
public interface DefaultAsyncService {

    /**
     * provider可以只实现普通sayHello方法
     */
    String sayHello(String name);

    /**
     * consumer可以直接调用default方法实现异步sayHello
     */
    default CompletableFuture sayHello(String name, boolean isAsync) {
        return CompletableFuture.completedFuture(sayHello(name));
    }
}
