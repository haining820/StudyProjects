package com.haining820.T;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-10-04
 * Time: 17:30
 */
public class TDemo {

    public static <T> void process(ArrayList<T> t) {
        System.out.println(t.getClass());
    }

    public static void test1() {
        Student<String> student = new Student<>();
        student.setName("小明");
        student.setDesc("我是小明");
        System.out.println(student);


        process(new ArrayList<Student>());
    }

    public static void main(String[] args) {
//        test1();
        Show<String> show = new Show<String>();
//        show.print1(new Integer(1));    // 不能编译
        show.print1("1");
        show.print2(new Integer(1));    // 可以编译

    }

}

class Show<T> {

    /**
     * 该方法的参数类型必须和Demo的泛型类型相同
     */
    public void print1(T t) {
        System.out.println(t);
    }

    /**
     * <T>表示自定义泛型T，可以传入任何类型的参数
     */
    public <T> void print2(T t) {
        System.out.println(t);
    }
}