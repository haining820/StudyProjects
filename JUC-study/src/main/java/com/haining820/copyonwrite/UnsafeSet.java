package com.haining820.copyonwrite;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-20
 * Time: 17:09
 */
public class UnsafeSet {

    public static void main(String[] args) {
        // Set<String> set = new HashSet<>();
        // Set<String> set = Collections.synchronizedSet(new HashSet<>()); // 解决方案1
        Set<String> set = new CopyOnWriteArraySet<>(); // 解决方案2
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
