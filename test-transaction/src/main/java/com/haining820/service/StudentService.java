package com.haining820.service;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-30
 * Time: 18:01
 */

public interface StudentService {

    int myUpdateStudent(String name);

    int myAddStudent();

    void testTranNever();

    int testTranRequired(String name);

    int testTranNested(String name);

    int testTranNested2(String name);

    int testTranRequiresNew(String name);

    int testTranRequiresNew2(int id, String name);

    int testTranNotSupported(int id, String name);

    int testTranNotSupported2(int id, String name);
    int testTranMandatory(int id, String name);




}
