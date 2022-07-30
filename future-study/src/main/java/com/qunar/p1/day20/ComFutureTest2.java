package com.qunar.p1.day20;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-20
 * Time: 11:00
 */

import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * @ClassName ComFutureTest
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-20 11:00
 */
public class ComFutureTest2 {

    private static ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);

    private static Logger LOGGER = Logger.getLogger(ComFutureTest2.class);

    public static void testComFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        EXECUTOR.execute(() -> {
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            completableFuture.complete("yhn");
        });
//        completableFuture.thenAccept(s -> LOGGER.info("s->" + s));
        CompletableFuture<String> future = completableFuture.applyToEither(fastFail(5, TimeUnit.SECONDS), Function.identity()).exceptionally(e -> "time out error!");
        LOGGER.info(future.get());
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);

    }


    static ScheduledThreadPoolExecutor SCHEDULED_EXECUTOR;

    static {
        SCHEDULED_EXECUTOR = new ScheduledThreadPoolExecutor(1, new MyThreadFactory());
        SCHEDULED_EXECUTOR.setRemoveOnCancelPolicy(true);
    }

    // ThreadFactory是一个接口类，也就是我们经常说的线程工厂，只有一个方法，可以用于创建线程
    // 默认情况下，ThreadPoolExecutor构造器传入的ThreadFactory 参数是Executors类中的defaultThreadFactory(),在这里进行自定义
    static final class MyThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);  // 设置该线程为守护线程
            t.setName("CompletableFutureDelayScheduler");
            return t;
        }
    }

    private static <T> CompletableFuture<T> fastFail(long timeout, TimeUnit timeUnit) {
        final CompletableFuture<T> future = new CompletableFuture<>();// 承载超时异常的future
        SCHEDULED_EXECUTOR.schedule(() -> {
            final TimeoutException ex = new TimeoutException("超时啦... " + timeout);
            return future.completeExceptionally(ex);
        }, timeout, timeUnit);
        return future;
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testComFuture();
    }

}

