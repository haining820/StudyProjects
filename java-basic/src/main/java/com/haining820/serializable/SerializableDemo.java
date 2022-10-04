package com.haining820.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-10-04
 * Time: 21:54
 */
public class SerializableDemo {

    public static void serialize() throws IOException {
        Student student = new Student("Tom", 30, "I am Tom","tom666");
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(new FileOutputStream(new File("student.txt")));
        objectOutputStream.writeObject(student);
        objectOutputStream.close();
        System.out.println("=========序列化成功！已经生成student.txt文件=========");
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream =
                new ObjectInputStream(new FileInputStream(new File("student.txt")));
        Student student = (Student) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println("反序列化结果为：");
        System.out.println(student);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serialize();
        deserialize();
    }

}
