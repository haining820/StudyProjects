package com.haining820.service.impl;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-10
 * Time: 10:04
 */

import com.haining820.service.DefaultAsyncService;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName DefaultAsyncServiceImpl
 * @Description TODO
 */
public class DefaultAsyncServiceImpl implements DefaultAsyncService {


    @Override
    public String sayHello(String name) {
        return "hello" + name;
    }

}
