package com.qunar.test;

import com.qunar.service.TeacherService;
import com.qunar.service.TransactionalTestService;
import com.qunar.service.impl.StudentServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-30
 * Time: 18:08
 */
public class TestTran {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    StudentServiceImpl studentService = (StudentServiceImpl) applicationContext.getBean("studentServiceImpl");

    TeacherService teacherService = (TeacherService) applicationContext.getBean("teacherServiceImpl");

    TransactionalTestService transactionalTestService = (TransactionalTestService) applicationContext.getBean("transactionalTestServiceImpl");

    /**
     * 不添加事务控制，后面的delete失败了但是前边的add依然可以成功
     */
    @Test
    public void testTran1() {
        studentService.testTranFail();
    }

    /**
     * 测试普通的无返回值的事务
     */
    @Test
    public void testTran2() {
        studentService.testTranWithoutResult();
    }

    /**
     * 测试对业务进行手动回滚
     */
    @Test
    public void testTran3() {
        studentService.testTranSetRollback();
    }

    /**
     * 测试有返回值的事务
     */
    @Test
    public void testTran4() {
        studentService.testTranWithResult();
    }

    /**
     * 使用@Transactional注解，默认等级REQUIRED，新建一个事务，也可以实现之前类似testTranWithoutResult的功能
     */
    @Test
    public void testAnnoTran1() {
        studentService.testAnnoTran();
    }

    /**
     * 同类之间的方法调用：REQUIRED调用NEVER
     */
    @Test
    public void testAnnoTran2() {
        studentService.testTestAnnoTranNever();
    }


    /**
     * 同类之间的方法调用：NEVER调用REQUIRED
     */
    @Test
    public void testAnnoTran3() {
        studentService.testInsertStudent();
    }


    /**
     * 使用声明式事务不用修改代码即可实现事务
     */
    @Test
    public void testAopTran() {
        teacherService.testAopTran();
    }

    /**
     * 不同类之间的方法调用：REQUIRED调用NEVER
     * 测试NEVER
     */
    @Test
    public void testNever() {
        transactionalTestService.testTranNever();
    }

    /**
     * 不同类之间的方法调用：REQUIRED调用REQUIRED
     * 测试REQUIRED
     */
    @Test
    public void testRequired() {
        transactionalTestService.testTranRequired();
    }

    /**
     * 不同类之间的方法调用：REQUIRED调用NESTED
     * 测试NESTED
     */
    @Test
    public void testNested() {
        transactionalTestService.testTranNested();
    }

    @Test
    public void testNested2() {
        transactionalTestService.testTranNested2();
    }

    /**
     * 不同类之间的方法调用：REQUIRED调用REQUIRES_NEW
     * 测试REQUIRES_NEW
     */
    @Test
    public void testRequiresNew() {
        transactionalTestService.testTranRequiresNew();
    }

    @Test
    public void testRequiresNew2() {
        transactionalTestService.testTranRequiresNew2();
    }

    /**
     * 不同类之间的方法调用：REQUIRED调用NOT_SUPPORTED
     * 测试NOT_SUPPORTED
     */
    @Test
    public void testNotSupported() {
        transactionalTestService.testTranNotSupported();
    }

    @Test
    public void testNotSupported2() {
        transactionalTestService.testTranNotSupported2();
    }

    /**
     * 不同类之间的方法调用：REQUIRED调用MANDATORY
     * 测试MANDATORY
     */
    @Test
    public void testMandatory() {
        transactionalTestService.testTranMandatory();
    }


}
