package com.qunar.test;

import com.qunar.pojo.Student;
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

    StudentServiceImpl service = (StudentServiceImpl) applicationContext.getBean("studentServiceImpl");

    @Test
    public void testT1() {
        service.testTranWithoutResult();
    }

    @Test
    public void testT2() {
        service.testTranSetRollback();
    }

    @Test
    public void testT3() {
        service.testTranWithResult();
    }
}
