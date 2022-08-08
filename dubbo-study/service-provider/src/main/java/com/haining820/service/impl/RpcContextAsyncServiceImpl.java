package com.haining820.service.impl;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-07
 * Time: 23:06
 */

import com.haining820.service.RpcContextAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RpcContextAsyncServiceImpl implements RpcContextAsyncService {

    private static Logger LOGGER = LoggerFactory.getLogger(CompletableFutureAsyncServiceImpl.class);


    /**
     * 测试dubbo的异步实现方式：RpcContext（2.6）
     */
    @Override
    public String sayHelloAsync(String name) {
        LOGGER.info("async provider received: " + name);
        return "hello, " + name;
    }



}
