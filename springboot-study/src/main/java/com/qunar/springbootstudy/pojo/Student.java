package com.qunar.springbootstudy.pojo;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-29
 * Time: 10:22
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName Student
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-29 10:22
 */

@Component("student888")
public class Student {
    @Value("yuhaiyang")
    private String name;

    @Value("18")
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
