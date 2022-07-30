package com.qunar.futureinb;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-21
 * Time: 10:33
 */

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName ThenApplyTest
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-21 10:33
 */
public class ThenApplyTest {

    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白吃好了");
        SmallTool.printTimeAndThread("小白 结账、要求开发票");

        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员收款 500元");
            SmallTool.sleepMillis(100);
            return "500";
        }).thenApplyAsync(money -> {
            SmallTool.printTimeAndThread(String.format("服务员开发票 面额 %s元", money));
            SmallTool.sleepMillis(200);
            return String.format("%s元发票", money);
        });

        SmallTool.printTimeAndThread("小白 接到朋友的电话，想一起打游戏");

        SmallTool.printTimeAndThread(String.format("小白拿到%s，准备回家", invoice.join()));
    }

}
