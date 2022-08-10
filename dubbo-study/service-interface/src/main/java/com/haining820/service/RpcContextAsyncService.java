package com.haining820.service;

/**
 * Created with IntelliJ IDEA
 * Description: 测试dubbo的异步实现方式：RpcContext
 * User: hn.yu
 * Date: 2022-08-07
 * Time: 23:06
 */

public interface RpcContextAsyncService {

    String sayHelloAsync(String name);

}
