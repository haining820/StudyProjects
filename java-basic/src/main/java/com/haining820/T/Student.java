package com.haining820.T;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-10-04
 * Time: 17:27
 */
public class Student<T> {

    private T name;

    private T desc;

    public Student() {

    }

    public Student(T name, T desc) {
        this.name = name;
        this.desc = desc;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public T getDesc() {
        return desc;
    }

    public void setDesc(T desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + name +
                ", desc=" + desc +
                '}';
    }
}
