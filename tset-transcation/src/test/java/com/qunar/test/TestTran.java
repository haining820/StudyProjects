package com.qunar.test;

import com.qunar.service.TeacherService;
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

    @Test
    public void testTran1() {
        studentService.testTran();
    }

    @Test
    public void testTran2() {
        studentService.testTranWithoutResult();
    }

    @Test
    public void testTran3() {
        studentService.testTranSetRollback();
    }

    @Test
    public void testTran4() {
        studentService.testTranWithResult();
    }

    @Test
    public void testAopTran() {
        teacherService.testAopTran();
    }


}
