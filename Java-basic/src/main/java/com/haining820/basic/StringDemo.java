package com.haining820.basic;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-28
 * Time: 20:04
 */
public class StringDemo {
    /**
     * 一个Integer对象转换为String。怎么转换？.
     */
    public static void main(String[] args) {
        /**
         * 正常情况下没问题，但是当Integer为null的时候会出现问题
         */
        Integer numB = null;
        System.out.println(String.valueOf(numB));   // null（会被转换为字符串，内容是"null"）
//        System.out.println(numB.toString());        // 会报异常NullPointerException
        System.out.println(StringUtils.valueOf(numB));  // 解决
        String str = null;
        String res = "" + str;  // 项目中有这个写法，与String.valueOf有相同的问题
        System.out.println(res);

        String str1 = "hello" + " word!";
        String str2 = "hello word!";
        System.out.println(str1 == str2);   // 在字符串常量池中是同一个对象，true

        String str3 = "hello ";
        String str4 = str3 + "word!";
        System.out.println(str2 == str4);   // 在堆中新建了其他对象，false
    }
}
