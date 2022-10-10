package com.haining820.typereference;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-10-04
 * Time: 18:00
 */
public class TypeReferenceDemo {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);

        JSONObject o = new JSONObject();
        o.put("k", list);

        // list中加入Integer类型元素，这里获取的List对象就是元素类型Integer
        List<Integer> types = o.getObject("k", List.class);
        System.out.println(JSON.toJSONString(types));

        // 通过TypeReference将List<Integer>中泛型映射成List<String>类型
        TypeReference<List<String>> reference = new TypeReference<List<String>>() {
        };
        List<String> types2 = o.getObject("k", reference);
        System.out.println(JSON.toJSONString(types2));

        List<Integer> integers = Arrays.asList(1, 2, 3);
        System.out.println(integers.toString());

    }

}
