package com.haining820.futureapi;

import com.haining820.utils.SmallTool;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * Created with IntelliJ IDEA
 * Description: 测试supplyAsync和runAsync
 * User: hn.yu
 * Date: 2022-07-21
 * Time: 10:05
 */

public class _01_supplyAsync {

    /**
     * supplyAsync 用于有返回值的任务
     */
    @Test
    public void supplyAsyncTest(){
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(200);
            SmallTool.printTimeAndThread("厨师打饭");
            SmallTool.sleepMillis(100);
            return "番茄炒蛋 + 米饭 做好了";
        });
        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s ,小白开吃", cf.join()));
    }

    @Test
    public void runAsyncTest(){
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 + 一碗米饭");

        // runAsync无返回值
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(200);
            SmallTool.printTimeAndThread("厨师打饭");
            SmallTool.sleepMillis(100);
            SmallTool.printTimeAndThread("番茄炒蛋 + 米饭 做好了");
        });
        SmallTool.printTimeAndThread("小白在打王者");
        // cf.join()获取不到值，因为runAsync没有返回结果
        SmallTool.printTimeAndThread(String.format("%s ,小白开吃", cf.join()));
    }

}