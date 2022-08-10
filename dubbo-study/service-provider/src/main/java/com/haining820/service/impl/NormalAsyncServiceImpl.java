package com.haining820.service.impl;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-10
 * Time: 10:58
 */

import com.haining820.service.NormalAsyncService;

/**
 * @ClassName NormalAsyncServiceImpl
 * @Description TODO
 */
public class NormalAsyncServiceImpl implements NormalAsyncService {

    @Override
    public String sayHelloAsync(String name) {
        return "normal async service: hello," + name;
    }
}
