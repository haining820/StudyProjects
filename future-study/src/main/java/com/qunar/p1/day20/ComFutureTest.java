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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
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
public class ComFutureTest {

    private static ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);

    private static Logger LOGGER = Logger.getLogger(CompletableFutureTestDay20.class);

    private static void timeoutProcess(CompletableFuture<String> completableFuture) {
        int timeoutSeconds = 10;
        Uninterruptibles.sleepUninterruptibly(timeoutSeconds, TimeUnit.SECONDS);
        completableFuture.complete("timeout exception");
    }

    public static void testComFuture() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        EXECUTOR.execute(() -> {
            Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
            completableFuture.complete("yhn");
        });
        completableFuture.thenAccept(s -> LOGGER.info("s->" + s));
//        timeoutProcess(completableFuture);
        CompletableFuture<String> within = within(completableFuture, 5, TimeUnit.SECONDS);
//        completableFuture.applyToEither(within, s -> LOGGER.info("s->" + s));
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);

    }

    public static <T> CompletableFuture<T> within(CompletableFuture<T> future, long timeout, TimeUnit unit) {
        final CompletableFuture<T> timeoutFuture = timeoutAfter(timeout, unit);
        // 哪个先完成 就apply哪一个结果
        return future.applyToEither(timeoutFuture, Function.identity());
    }

    public static <T> CompletableFuture<T> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<T> result = new CompletableFuture<T>();
        // timeout 时间后 抛出TimeoutException 类似于sentinel / watcher
        Delayer.delayer.schedule(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);
        return result;
    }

    static final class Delayer {

        static ScheduledThreadPoolExecutor delayer;

        static {
            delayer = new ScheduledThreadPoolExecutor(1, new MyThreadFactory());
            delayer.setRemoveOnCancelPolicy(true);
        }


        static ScheduledFuture<?> delay(Runnable command, long delay, TimeUnit unit) {
            return delayer.schedule(command, delay, unit);
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

    }


    public static void main(String[] args) {
        testComFuture();
    }

}

