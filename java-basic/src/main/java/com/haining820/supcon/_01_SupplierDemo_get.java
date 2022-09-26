package com.haining820.supcon;

import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-26
 * Time: 17:40
 */
public class _01_SupplierDemo_get {
    // 定义一个方法，方法的参数传递Supplier<T>接口，泛型设置为String，get方法就会返回一个String
    public static String getString(Supplier<String> sup){
        return sup.get();
    }

    public static void helloSupplier(){
        // 调用getString方法，方法参数Supplier是一个函数式接口，所以可以传递Lambda表达式
        String s = getString(() -> {
            // 生产一个字符串，并返回
            return "hello,supplier!";
        });
        System.out.println(s);
    }

    public static void byeSupplier(){
        // 简化
        String s1 = getString(() -> "goodbye,supplier!");
        System.out.println(s1);
    }

    public void supplierTest(){
        // 传一个字符串：hello world，使用get返回这个字符串
        Supplier<String> supplier = () -> "hello world";
        System.out.println(supplier.get());

        // 传一个对象，使用get返回这个对象
        Supplier<Student> studentSupplier = () -> new Student("小明",12);
        System.out.println(studentSupplier.get());
    }


    public static void main(String[] args) {
        helloSupplier();
        byeSupplier();
        System.out.println("================");
        _01_SupplierDemo_get a01SupplierDemoAccept = new _01_SupplierDemo_get();
        a01SupplierDemoAccept.supplierTest();
    }


    class Student{
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
