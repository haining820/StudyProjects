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

    @Test
    public void testStream() {
        List<Integer> originList = Lists.newArrayList();
        // 10w条数据，添加到list中
        for (int i = 0; i < 100000; i++) {
            originList.add(i);
        }
        System.out.println("originList.size: " + originList.size());
        List<Integer> streamList = Lists.newArrayList();
        List<Integer> parallelStreamList = Lists.newArrayList();
        List<Integer> foreachStreamList = Lists.newArrayList();
        originList.stream().forEach(streamList::add);
        originList.parallelStream().forEach(parallelStreamList::add);
        originList.forEach(foreachStreamList::add);
        System.out.println("streamList.size: " + streamList.size());
        System.out.println("parallelStreamList.size: " + parallelStreamList.size());
        System.out.println("foreachStreamList.size: " + foreachStreamList.size());
    }

}
