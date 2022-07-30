package com.qunar.springbootstudy;

import com.qunar.springbootstudy.controller.HelloController;
import com.qunar.springbootstudy.mapper.StudentMapper;
import com.qunar.springbootstudy.mapper.StudentMapperImpl;
import com.qunar.springbootstudy.pojo.Student;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

@SpringBootTest
//@RunWith(SpringRunner.class)
class SpringbootStudyApplicationTests {

    private static Logger LOGGER = Logger.getLogger(SpringbootStudyApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Resource(name = "student888")
    private Student student;

    @Test
    void testStudent() {
        LOGGER.info(student.toString());
    }

    @Test
    void testSelect() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        LOGGER.info(studentMapper.selectStudent());
    }


    @Test
    void testAddDelete() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student student = new Student("testadd", 23);
        LOGGER.info("add->" + studentMapper.addStudent(student));
        LOGGER.info("delete ->" + studentMapper.deleteStudent(100));
        LOGGER.info("select ->" + studentMapper.selectStudent());
    }





    @Test
    public void testTran() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloController controller = context.getBean("helloController", HelloController.class);
        controller.testTran();
    }


}
