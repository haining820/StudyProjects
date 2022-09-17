package com.haining820.futuretask;

import com.haining820.CallableTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: yuhaining
 * Date: 2022-07-24
 * Time: 22:01
 */

@Slf4j
public class FutureTaskDemo {
    
    public static void testFT() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new CallableTask());

        // 获得当前futureTask的状态
        log.info("isDone1->" + futureTask.isDone());    // false
        new Thread(futureTask).start();
        log.info(futureTask.get());

        TimeUnit.SECONDS.sleep(10);

        log.info("isDone2->" + futureTask.isDone());    // true
        new Thread(futureTask).start();
        log.info(futureTask.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testFT();
    }

}
