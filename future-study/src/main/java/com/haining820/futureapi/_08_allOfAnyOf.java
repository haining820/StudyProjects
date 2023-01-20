package com.haining820.futureapi;

import com.google.common.collect.Lists;
import com.haining820.utils.SmallTool;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA
 * Description: 测试allOf和anyOf
 * User: hn.yu
 * Date: 2022-07-22
 * Time: 15:07
 */
public class _08_allOfAnyOf {

    // cf1会提前完成
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
        SmallTool.sleepMillis(10);
        SmallTool.printTimeAndThread("cf1 working...");
        return "cf1 任务完成";
    });

    CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
        SmallTool.sleepMillis(500);
        SmallTool.printTimeAndThread("cf2 working...");
        return "cf2 任务完成";
    });

    CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
        SmallTool.sleepMillis(500);
        SmallTool.printTimeAndThread("cf3 working...");
//        int i = 1 / 0;
        return "cf3 任务完成";
    });


    // cf1会提前完成
    CompletableFuture<Student> cfa = CompletableFuture.supplyAsync(() -> {
        SmallTool.sleepMillis(100);
        SmallTool.printTimeAndThread("cfa working...");
        return new Student("cfa", 13);
    });

    CompletableFuture<Student> cfb = CompletableFuture.supplyAsync(() -> {
        SmallTool.sleepMillis(5);
        SmallTool.printTimeAndThread("cfb working...");
        return new Student("cfb", 133);
    });

    CompletableFuture<Student> cfc = CompletableFuture.supplyAsync(() -> {
        SmallTool.sleepMillis(500);
        SmallTool.printTimeAndThread("cfc working...");
//        int i = 1 / 0;
        return new Student("cfc", 13);
    });

    @Test
    public void testAllOf() {
        System.out.println("===========cfAll===========");
        /**
         * 有异常就抛异常
         * 没异常get()会返回null
         */
        CompletableFuture<Void> cfAll = CompletableFuture.allOf(cf1, cf2, cf3);
        try {
            System.out.println("cfAll->" + cfAll.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAnyOf() {
        System.out.println("===========cfAny===========");
        /**
         * cf3会出现异常，但是cf1提前完成，anyOf会提前返回结果
         * 此时程序直接结束，不会抛出异常
         */
        CompletableFuture<Object> cfAny = CompletableFuture.anyOf(cf1, cf2, cf3);
        try {
            System.out.println("cfAny->" + cfAny.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAnyOfStudent() {
        System.out.println("===========cfAny===========");
        List<CompletableFuture<Student>> cfList = Lists.newArrayList(cfa, cfb, cfc);
        CompletableFuture<Student>[] futures = cfList.toArray(new CompletableFuture[cfList.size()]);
/*        CompletableFuture<Student>[] cflist2 = new CompletableFuture[6];
        cflist2 = new CompletableFuture[]{cfa, cfb, cfc};*/
        CompletableFuture<Object> cfAny = CompletableFuture.anyOf(futures);
        try {
            Student student = (Student) cfAny.get();
            System.out.println("cfAny_student1->" + student);
            student.setAge(888);
            System.out.println("cfAny_student2->" + student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Getter
    @Setter
    @ToString
    private class Student {

        String name;

        Integer age;

        public Student(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}