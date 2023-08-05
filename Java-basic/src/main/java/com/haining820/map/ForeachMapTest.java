package com.haining820.map;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-10-19
 * Time: 0:10
 */
public class ForeachMapTest {

    public void test() {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");

        HashMap<String, String> map2 = Maps.newHashMap();
//        map.forEach(map::putAll);
        System.out.println("commit test");
    }

    public void test2() {
        HashMap map1 = new HashMap();
        map1.put("111", "AAA");
        System.out.println(map1);
        HashMap map2 = new HashMap();
        map2.put("222", "BBB");
        map2.put("333", "CCC");
        System.out.println(map2);
        map1.putAll(map2);
        System.out.println(map1);
    }

    public static void main(String[] args) {
        ForeachMapTest foreachMapTest = new ForeachMapTest();
        foreachMapTest.test2();
    }

}
