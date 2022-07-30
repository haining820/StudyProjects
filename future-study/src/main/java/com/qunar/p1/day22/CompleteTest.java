package com.qunar.p1.day22;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-22
 * Time: 11:19
 */

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.qunar.CallableTask;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * @ClassName CompleteTest
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-22 11:19
 */
public class CompleteTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("3->"+Thread.currentThread().getName());
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> lf = pool.submit(new CallableTask());
        CompletableFuture<String> cf = FutureUtil.convert(lf);
        System.out.println(cf.get());
    }

}
