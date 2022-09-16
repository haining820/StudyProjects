package com.haining820.service;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-03
 * Time: 16:06
 */
public interface TransactionalTestService {

    void testTranNever();

    void testTranRequired();

    void testTranNested();

    void testTranNested2();

    void testTranRequiresNew();

    void testTranRequiresNew2();

    void testTranNotSupported();

    void testTranNotSupported2();

    void testTranMandatory();


}
