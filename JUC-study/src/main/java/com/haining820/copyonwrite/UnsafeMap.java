package com.haining820.copyonwrite;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-20
 * Time: 17:10
 */
public class UnsafeMap {

    public static void main(String[] args) {
        // Map<String, String> map = new HashMap<>();
        // Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());  // 1
        Map<String, String> map = new ConcurrentHashMap<>();    // 2
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
