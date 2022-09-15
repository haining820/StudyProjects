package com.qunar.pojo;
/**
 * Created with IntelliJ IDEA
 * Description: 老师实体类，测试声明式事务
 * User: hn.yu
 * Date: 2022-08-01
 * Time: 11:55
 */

/**
 * @ClassName Teacher
 * @Description 老师实体类
 */
public class Teacher {

    private String name;
    private int age;

    private String content;

    public Teacher(String name, int age, String content) {
        this.name = name;
        this.age = age;
        this.content = content;
    }

    public Teacher() {
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", content='" + content + '\'' +
                '}';
    }
}
