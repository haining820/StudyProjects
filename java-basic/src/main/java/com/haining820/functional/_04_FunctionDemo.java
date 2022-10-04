package com.haining820.functional;

import java.util.function.Function;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-10-04
 * Time: 16:33
 */
public class _04_FunctionDemo {

    public static void functionTest(String str, Function<String,String> function) {
        System.out.println(function.apply(str));
    }

    public static void main(String[] args) {

        functionTest("hhh",str->{
            // 将输入的小写字母转换为大写
            return str.toUpperCase();
        });

    }
}
