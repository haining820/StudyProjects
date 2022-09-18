package com.haining820.futureconvert;


import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA
 * Description: FutureUtils工具类，实现ListenableFuture和CompletableFuture的相互转换
 * User: hn.yu
 * Date: 2022-07-22
 * Time: 10:09
 */

public class FutureUtil {

    public static <T> CompletableFuture<T> convert(ListenableFuture<T> lf) {
        // CompletableFuture中的线程默认是守护线程，这里也可以选择设为守护线程
/*        return convert(lf, Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFuture");
                return t;
            }
        }));*/
        return convert(lf, Executors.newSingleThreadExecutor());
    }

    // 重载
    public static <T> CompletableFuture<T> convert(ListenableFuture<T> lf, Executor executor) {
        if (lf == null) {
            return null;
        }
        CompletableFuture<T> cf = new CompletableFuture<>();
        lf.addListener(() -> {
            T value = null;
            try {
                value = lf.get();
                cf.complete(value);
            } catch (InterruptedException | ExecutionException e) {
                cf.completeExceptionally(e);
            }
        }, executor);
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