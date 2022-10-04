package com.haining820;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-29
 * Time: 16:30
 */
public class ImmutableMapDemo {

    public void newMapTest() {
        /**
         * 使用Builder创建ImmutableMap
         * Map<String, String> immutableMap = new ImmutableMap.Builder<String, String>().build();
         */
        Map<String, String> immutableMap = new ImmutableMap.Builder<String, String>()
                .put("1", "aa")
                .put("2", "bb")
                .build();
        System.out.println(immutableMap.toString());
    }

    public void ofTest() {
        /**
         * 使用of创建，最多5对kv
         */
        Map<String, String> immutableMap = ImmutableMap.of(
                "3", "cc",
                "4", "dd"
        );
        System.out.println(immutableMap);
    }

    public static void main(String[] args) {
        ImmutableMapDemo demo = new ImmutableMapDemo();
        demo.ofTest();
    }

}
