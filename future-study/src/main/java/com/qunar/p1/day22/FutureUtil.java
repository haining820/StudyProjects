package com.qunar.p1.day22;
/**
 * Created with IntelliJ IDEA
 * Description: FutureUtils工具类，实现ListenableFuture和CompletableFuture的相互转换
 * User: hn.yu
 * Date: 2022-07-22
 * Time: 10:09
 */

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @ClassName FutureUtils
 * @Description FutureUtils工具类，实现ListenableFuture和CompletableFuture的相互转换
 * @User hn.yu
 * @Date 2022-07-22 10:09
 */
public class FutureUtil {

    public static <T> CompletableFuture<T> convert(ListenableFuture<T> lf) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return convert(lf, Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFuture");
                return t;
            }
        }));
    }

    public static <T> CompletableFuture<T> convert(ListenableFuture<T> lf, Executor executor) {
        if (lf == null) {
            return null;
        }
        CompletableFuture<T> cf = new CompletableFuture<>();
        lf.addListener(() -> {
            T value = null;
            try {
                value = lf.get();
                System.out.println("666");
                System.out.println(Thread.currentThread().isDaemon());
                System.out.println("1->"+Thread.currentThread().getName());
                cf.complete(value);
            } catch (InterruptedException | ExecutionException e) {
                cf.completeExceptionally(e);
            }
        }, executor);
        System.out.println("2->"+Thread.currentThread().getName());
        return cf;
    }


    public static <T> ListenableFuture<T> convert(CompletableFuture<T> cf) {
        if (cf == null) {
            return null;
        }
        SettableFuture<T> lf = SettableFuture.create();
        cf.whenComplete((r, t) -> {
            if (t != null) {
                lf.setException(t);
            } else {
                lf.set(r);
            }
        });
        return lf;
    }

}
