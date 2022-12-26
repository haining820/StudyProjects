package com.haining820.test;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-03
 * Time: 17:45
 */

import com.haining820.mapper.StudentMapper;
import com.haining820.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestDB {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    StudentService studentService = (StudentService) applicationContext.getBean("studentServiceImpl");



    @Test
    public void testUpdate(){
        String name = "testUpdate->" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        studentService.myUpdateStudent(name);
    }

//    @Resource
//    StudentMapper studentMapper;

    StudentMapper studentMapper = (StudentMapper) applicationContext.getBean("studentMapper");


    @Test
    public void testUpdate2(){
        System.out.println(studentMapper.testSelect("xiomy87imi"));

    }

}
