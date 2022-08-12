package com.qunar.day20;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-20
 * Time: 11:00
 */

import com.google.common.util.concurrent.Uninterruptibles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @ClassName ComFutureTest
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-20 11:00
 */
@Component
public class ComFuture {

    private static ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);

    private static Logger LOGGER = LoggerFactory.getLogger(ComFuture.class);

    @Autowired
    ITimeHelper timeHelper;


    public void testComFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        EXECUTOR.execute(() -> {
            Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
            completableFuture.complete("yhn");
        });
        CompletableFuture<String> future = completableFuture
                .applyToEither(timeHelper.fastFail(5, TimeUnit.SECONDS), Function.identity())
                .exceptionally(e -> "time out error!");
        LOGGER.info(future.get());
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
    }


}

