package com.haining820.springbootstudy;

import com.haining820.springbootstudy.controller.HelloController;
import com.haining820.springbootstudy.mapper.StudentMapper;
import com.haining820.springbootstudy.pojo.Student;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
