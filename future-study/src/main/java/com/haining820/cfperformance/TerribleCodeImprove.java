package com.haining820.cfperformance;


import com.haining820.utils.SmallTool;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;


/**
 * 小白点菜，需要做10盘菜
 */
public class TerribleCodeImprove {

    public static void main(String[] args) {
        /**
         * 如何提升性能？将线程数设置为一个合适的值，根据长期线上观测确定
         * 但是该参数不只为cf服务，而且启动后无法动态修改
         * 一般会重新创建线程池供cf单独使用（即之前futureapi中对应的Executor中相关的内容）
         * 创建线程池之后要记得关闭（或设置为守护线程） 好处：彼此隔离，方便控制
         */
        // 修改默认线程数
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","19");
        // 查看当前线程数
        System.out.println("ForkJoinPool.commonPool().getPoolSize()->" + ForkJoinPool.commonPool().getPoolSize());
        // 查看最大线程数
        System.out.println("ForkJoinPool.getCommonPoolParallelism()->" + ForkJoinPool.getCommonPoolParallelism());

        SmallTool.printTimeAndThread("小白和小伙伴们进餐厅点菜");
        long startTime = System.currentTimeMillis();
        ArrayList<Dish> dishes = new ArrayList<>();

        // 点菜
        /**
         * 16-1：当前CPU支持的最大线程数-1，即cf底层用到的线程池中的最大线程数，超过这个值了线程池就满了，任务比较多，会消耗更长的时间
         * ForkJoinPool.getCommonPoolParallelism() = 15
         * 如果使用上边的setProperty方法，ForkJoinPool.getCommonPoolParallelism()就是set的值
         */
        for (int i = 1; i <= 16 - 1; i++) {
            Dish dish = new Dish("菜" + i, 1);
            dishes.add(dish);
        }

        // 做菜
        ArrayList<CompletableFuture> cfList = new ArrayList<>();
        for (Dish dish : dishes) {
            CompletableFuture cf = CompletableFuture.runAsync(() -> dish.makeUseCPU());
            cfList.add(cf);     // 将异步任务收集到一个容器中
        }
        CompletableFuture[] cfArray = cfList.toArray(new CompletableFuture[cfList.size()]);
        CompletableFuture.allOf(cfArray).join();    // 等待容器中的所有任务执行完毕

        SmallTool.printTimeAndThread("菜都做好了，上桌 " + (System.currentTimeMillis() - startTime));
    }
}
