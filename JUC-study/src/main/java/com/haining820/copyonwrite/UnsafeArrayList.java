package com.haining820.copyonwrite;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-20
 * Time: 17:08
 */
public class UnsafeArrayList {
    public static void main(String[] args) {
        // List<String> list = new ArrayList<>();	// 并发下ArrayList是不安全的，会有并发修改异常ConcurrentModificationException
        // List<String> list = new Vector<>();  	// 解决方案1
        // List<String> list = Collections.synchronizedList(new ArrayList<>());    // 解决方案2，synchronized效率比较低
        List<String> list = new CopyOnWriteArrayList<>();   // 解决方案3
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
