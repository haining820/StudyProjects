package com.haining820.futureapi;

import com.haining820.utils.SmallTool;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-21
 * Time: 10:48
 */

public class _06_exceptionally {

    @Test
    public void testEnd() {
        SmallTool.printTimeAndThread("张三走出餐厅，来到公交站");
        SmallTool.printTimeAndThread("等待 700路 或者 800路 公交到来");
        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("700路公交正在赶来");
            // 调整时间，700路一定先到
            SmallTool.sleepMillis(400);
            return "700路到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("800路公交正在赶来");
            SmallTool.sleepMillis(200);
            return "800路到了";
        }), firstComeBus -> {
            SmallTool.printTimeAndThread(firstComeBus);
            // 如果先到的是700路，会出现异常
            if (firstComeBus.startsWith("700")) {
                throw new RuntimeException("撞树了...");
            }
            return firstComeBus;
        }).exceptionally(e -> {
            SmallTool.printTimeAndThread(e.getMessage());
            SmallTool.printTimeAndThread("小白叫出租车");
            return "出租车 叫到了";
        });
        SmallTool.printTimeAndThread(String.format("%s,小白坐车回家", bus.join()));
    }


    /**
     * exceptionally也可以放在链式调用的中间进行判断
     */
    @Test
    public void testMiddle() {
        SmallTool.printTimeAndThread("小白走出餐厅，来到公交站");
        SmallTool.printTimeAndThread("等待 700路 或者 800路 公交到来");
        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            // 并行执行
            SmallTool.printTimeAndThread("700路公交正在赶来");
            SmallTool.sleepMillis(100);
            if ("700路公交".startsWith("700")) {
                throw new RuntimeException("700路撞树了...");
            }
            return "700路到了";
        }).exceptionally(e -> {
            // 该exceptionally只对上边的第一个supplyAsync进行处理
            SmallTool.printTimeAndThread(e.getMessage());
            SmallTool.printTimeAndThread("700路撞树了，小白骑共享单车回家");
            return "小白骑共享单车";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            // 并行执行
            SmallTool.printTimeAndThread("800路公交正在赶来");
            SmallTool.sleepMillis(90);
            return "800路到了";
        }), firstComeBus -> {
            SmallTool.printTimeAndThread(firstComeBus);
            return firstComeBus;
        }).exceptionally(e -> {
            /**
             * 该exceptionally对上边两个supplyAsync进行处理
             * 由于第一个supplyAsync出现异常已经被处理，future任务bus已经获得结果返回，程序并不会运行到这里
             */
            SmallTool.printTimeAndThread(e.getMessage());
            SmallTool.printTimeAndThread("小白叫出租车");
            return "出租车 叫到了";
        });
        SmallTool.printTimeAndThread(String.format("%s,小白回家", bus.join()));
    }

    public void test3() {
        // 实例化一个CompletableFuture，返回值是Integer，返回null
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return null;
        });
        // 制造一个空指针异常
        CompletableFuture<String> exceptionally = future.thenApply(result -> {
            int i = result;
            return i;
        }).thenApply(result -> {
            // 这里不会执行，因为上面出现了异常
            String words = "现在是" + result + "点钟";
            return words;
        }).exceptionally(error -> {
            // 选择在这里打印出异常
            error.printStackTrace();
            // 并且当异常发生的时候，返回一个默认的文字
            return "出错啊~";
        });
        // 即exceptionally.thenAccept(res -> System.out.println(res));
        exceptionally.thenAccept(System.out::println);
    }
}
