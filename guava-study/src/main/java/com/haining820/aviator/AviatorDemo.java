package com.haining820.aviator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2023-10-08
 * Time: 14:19
 */
public class AviatorDemo {

    public static void main(String[] args) {
        String expression = "a + b + c";

        Map<String, Object> params = new HashMap<>();
        params.put("a", 1);
        params.put("b", 2);
        params.put("c", 3);

        long result = (long) AviatorEvaluator.execute(expression, params);

        System.out.printf("result : " + result);
    }

}
