package com.haining820.timeoutfuture;

import com.google.common.util.concurrent.Uninterruptibles;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created with IntelliJ IDEA
 * Description: 对超时的future进行处理
 * User: hn.yu
 * Date: 2022-07-20
 * Time: 11:00
 */
@Slf4j
@Component
public class TimeoutFutureProcess {

    private static ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);

    @Autowired
    ITimeHelper timeHelper;

    public void process() throws ExecutionException, InterruptedException {
        // 老师给的例子：线程存在存在超时问题，需要进行处理
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        EXECUTOR.execute(() -> {
            Uninterruptibles.sleepUninterruptibly(30, TimeUnit.SECONDS);
            completableFuture.complete("yhn");
        });
        // 处理方式：引入另一个future，超时之后会返回异常
        CompletableFuture<String> future = completableFuture
                // 例子的future和超时future一起运行，谁先运行完返回谁
                .applyToEither(timeHelper.fastFail(5, TimeUnit.SECONDS), Function.identity())
                // 出现异常进行提示
                .exceptionally(e -> "time out error!");
        log.info(future.get());
    }

}

