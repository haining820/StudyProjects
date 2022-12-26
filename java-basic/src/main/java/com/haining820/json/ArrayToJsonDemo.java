package com.haining820.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-10-19
 * Time: 0:29
 */
public class ArrayToJsonDemo {

    public static void main(String[] args) throws JsonProcessingException {

        Stu stu1 = new Stu("v1", "v2");
        Stu stu2 = new Stu("v11","v22");
        Stu stu3 = new Stu("v111","v222");

        ArrayList<Stu> list = Lists.newArrayList();
        list.add(stu1);
        list.add(stu2);
        list.add(stu3);
        System.out.println(list);

        HashMap<String, Stu> map = Maps.newHashMap();

//        list.forEach(map::putAll);





        HashMap<String, Object> map2 = Maps.newHashMap();
        map2.put("key_map2",1);
        map2.put("key2_map2",2);


        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(map2);
        System.out.println(result);

    }

}
