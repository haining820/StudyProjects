package com.haining820.springbootstudy.service;

import com.haining820.springbootstudy.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-24
 * Time: 15:43
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Test
    public void testSelect(){
        List<Student> students = studentService.selectStudent();
        log.info(students.toString());
    }

}
