package com.qunar.test;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-03
 * Time: 17:45
 */

import com.qunar.service.StudentService;
import com.qunar.service.impl.StudentServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TestDB
 * @Description TODO
 */
public class TestDB {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    StudentService studentService = (StudentService) applicationContext.getBean("studentServiceImpl");



    @Test
    public void testUpdate(){
        String name = "testUpdate->" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        studentService.myUpdateStudent(name);
    }

}
