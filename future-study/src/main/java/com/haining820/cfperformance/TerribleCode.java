package com.haining820.cfperformance;


import com.haining820.utils.SmallTool;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;


/**
 * 小白点菜，需要做10盘菜
 */
public class TerribleCode {
    public static void main(String[] args) {

        SmallTool.printTimeAndThread("小白和小伙伴们进餐厅点菜");
        long startTime = System.currentTimeMillis();

        ArrayList<Dish> dishes = new ArrayList<>();
        // 点菜
        for (int i = 1; i <= 10; i++) {
            Dish dish = new Dish("菜" + i, 1);
            dishes.add(dish);
        }
        // 做菜
        /**
         * 存在的问题：当有一批异步任务的时候，一个一个的去执行，直接将异步变成了串行
         */
        for (Dish dish : dishes) {
            CompletableFuture.runAsync(() -> dish.make()).join();
        }

        SmallTool.printTimeAndThread("菜都做好了，上桌 " + (System.currentTimeMillis() - startTime));
    }
}
