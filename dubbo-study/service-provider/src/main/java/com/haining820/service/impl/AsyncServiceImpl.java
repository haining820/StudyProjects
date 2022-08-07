package com.haining820.service.impl;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-07
 * Time: 23:06
 */

import com.haining820.service.AsyncService;
import com.haining820.utils.MyTools;

/**
 * @ClassName AsyncServiceImpl
 * @Description TODO
 */
public class AsyncServiceImpl implements AsyncService {

    @Override
    public int addNum(int x, int y) {
        MyTools.sleepMillis(10000);
        return x + y;
    }

    @Override
    public int subNum(int x, int y) {
        MyTools.sleepMillis(2000);
        return x - y;
    }
}
