package com.haining820.supcon;

import java.util.function.Consumer;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-26
 * Time: 17:52
 */
public class _02_ConsumerDemo_accept {

    /**
     * 定义一个方法，方法的参数传递一个字符串的姓名
     * 方法的参数传递Consumer接口，泛型使用String，可以使用Consumer接口消费字符串的姓名
     */
    public static void method(String name, Consumer<String> con) {
        con.accept(name);
    }

    public static void consumerTest() {
        // 调用method方法，传递字符串姓名，方法的另一个参数是Consumer接口，是一个函数式接口，所以可以传递Lambda表达式
        method("小明", name -> {
            // 对传递的字符串进行消费，消费方式：直接输出字符串
            System.out.println(name);
            // 消费方式:把字符串进行反转输出
            String str = new StringBuilder(name).reverse().toString();
            System.out.println(str);
        });
    }

    public static void main(String[] args) {
        consumerTest();
    }
}
