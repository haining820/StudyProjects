package com.haining820.futuretask;

import org.apache.log4j.Logger;

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
public class FutTaskDemo {

    private Logger LOGGER = Logger.getLogger(FutTaskDemo.class);

    public void testDuTask() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new FuTaskCallable());

        LOGGER.info("isDone1->" + futureTask.isDone());
        new Thread(futureTask).start();
        LOGGER.info(futureTask.get());

        TimeUnit.SECONDS.sleep(10);

        LOGGER.info("isDone2->" + futureTask.isDone());
        new Thread(futureTask).start();
        LOGGER.info(futureTask.get());
    }

}
