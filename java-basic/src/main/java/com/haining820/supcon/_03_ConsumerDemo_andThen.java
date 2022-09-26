package com.haining820.supcon;

import java.util.function.Consumer;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-26
 * Time: 17:56
 */

/**
 * Consumer接口的默认方法andThen
 * 作用：需要两个Consumer接口，可以把两个Consumer接口组合到一起，在对数据进行消费
 * 例如:
 *   Consumer<String> con1
 *   Consumer<String> con2
 *   String s = "hello";
 *   con1.accept(s);
 *   con2.accept(s);
 *   连接两个Consumer接口 再进行消费
 *   con1.andThen(con2).accept(s);谁写前边谁先消费
 */
/**
 * 如果一个方法的参数和返回值全都是Consumer类型，
 * 那么就可以实现效果：消费数据的时候，首先做一个操作，然后再做一个操作，实现组合。
 * 这个方法就是Consumer接口中的default方法andThen
 */
public class _03_ConsumerDemo_andThen {

    // 定义一个方法，方法的参数传递一个字符串和两个Consumer接口，Consumer接口的泛型使用字符串
    public static void method(String s, Consumer<String> con1, Consumer<String> con2) {
        // con1.accept(s);
        // con2.accept(s);
        // 使用andThen方法，把两个Consumer接口连接到一起，再消费数据
        con1.andThen(con2).accept(s);
    }

    public static void main(String[] args) {
        // 调用method方法，传递一个字符串，两个lambda表达式
        method("hello",
                (t) -> {
                    // 消费方式：把字符串转换为大写输出
                    System.out.println(t.toUpperCase());
                },
                (t) -> {
                    // 消费方式：把字符串转换为小写输出
                    System.out.println(t.toLowerCase());
                });
    }

}
