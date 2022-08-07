package com.haining820.pojo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * Description: user实体类
 * User: hn.yu
 * Date: 2022-08-07
 * Time: 13:50
 */

public class User implements Serializable {

    private int id;
    private String name;
    private int age;
    private String content;

    public User() {
    }

    public User(int id, String name, int age, String content) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", content='" + content + '\'' +
                '}';
    }
}
