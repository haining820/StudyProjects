package com.haining820.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2023-04-28
 * Time: 16:18
 */
public class ParallelStreamDemo {

    /**
     * https://www.cnblogs.com/sueyyyy/p/13079525.html
     */
    @Test
    public void testStream() {
        List<Integer> originList = Lists.newArrayList();
        // 10w条数据，添加到list中
        for (int i = 0; i < 100000; i++) {
            originList.add(i);
        }
        System.out.println("originList.size: " + originList.size());
        List<Integer> list1 = Lists.newArrayList();
        List<Integer> list2 = Lists.newArrayList();
        List<Integer> list3 = Lists.newArrayList();
        originList.stream().forEach(list1::add);
        originList.parallelStream().forEach(list2::add);
        originList.forEach(list3::add);
        System.out.println("streamList.size: " + list1.size());
        System.out.println("parallelStreamList.size: " + list2.size());
        System.out.println("foreachStreamList.size: " + list3.size());
    }

}
