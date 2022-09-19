package com.haining820.futureapi;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-19
 * Time: 10:20
 */
public class FutureApiTest {

    _00_getjoin gj = new _00_getjoin();

    @Test
    public void test_00_getjoin_1() throws ExecutionException, InterruptedException {
        gj.getTest();
    }
    @Test
    public void test_00_getjoin_2() throws ExecutionException, InterruptedException {
        gj.timeoutGetTest();
    }
    @Test
    public void test_00_getjoin_3() throws ExecutionException, InterruptedException {
        gj.getNowTest();
    }


    _01_supplyAsync sa = new _01_supplyAsync();

    @Test
    public void test_01_supplyAsync_1() {
        sa.supplyAsyncTest();
    }

    @Test
    public void test_01_supplyAsync_2() {
        sa.runAsyncTest();
    }


    _02_thenCompose tc = new _02_thenCompose();

    @Test
    public void test_02_thenCompose_1() {
        tc.thenComposeTest();
    }

    @Test
    public void test_02_thenCompose_2() {
        tc.thenComposeAsyncTest();
    }


    _03_thenCombine tc2 = new _03_thenCombine();

    @Test
    public void test_03_thenCombine_1() {
        tc2.thenCombineTest();
    }


    _04_thenApply ta = new _04_thenApply();

    @Test
    public void test_04_thenApply_1() {
        ta.thenApplyTest();
    }

    @Test
    public void test_04_thenApply_2() {
        ta.thenApplyAsyncTest();
    }


    _05_applyToEither ate = new _05_applyToEither();

    @Test
    public void test_05_applyToEither_1() {
        ate.applyToEitherTest();
    }


    _06_exceptionally ecp = new _06_exceptionally();

    @Test
    public void test_06_exceptionally_1() {
        ecp.testEnd();
    }

    @Test
    public void test_06_exceptionally_2() {
        ecp.testMiddle();
    }

    @Test
    public void test_06_exceptionally_3() {
        ecp.test3();
    }


    _07_complete comp = new _07_complete();

    @Test
    public void test_07_complete_1() throws ExecutionException, InterruptedException {
        comp.completeTest();
    }


    _08_allOfAnyOf ao = new _08_allOfAnyOf();

    @Test
    public void test_08_allOfAnyOf_1() throws ExecutionException, InterruptedException {
        ao.testAllOfAnyOf();
    }


}
