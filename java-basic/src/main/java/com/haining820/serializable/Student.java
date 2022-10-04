package com.haining820.serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-10-04
 * Time: 22:00
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable {


    private static final long serialVersionUID = -4844659850613636533L;

    private static String msg = "hihihi";

    private String name;

    private Integer age;

    private String description;

    private transient String password;

//    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
//        objectOutputStream.defaultWriteObject();
//        // 手工检查反序列化后学生成绩的有效性，若发现有问题，即终止操作！
//        if (10 > age || 20 < age) {
//            throw new IllegalArgumentException("writeObject: 学生年龄只能在10到20之间！");
//        }
//    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        // 调用默认的反序列化函数
        objectInputStream.defaultReadObject();
        // 手工检查反序列化后学生成绩的有效性，若发现有问题，即终止操作！
        if (10 > age || 20 < age) {
            throw new IllegalArgumentException("readObject: 学生年龄只能在10到20之间！");
        }
    }

}
