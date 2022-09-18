package com.haining820;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haining820.entity.Student;
import com.haining820.mapper.StudentMapper;
import com.haining820.futureconvert.TimeUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisplusStudyApplicationTests {

    @Autowired
    private StudentMapper studentMapper;

    private static Logger LOGGER = LoggerFactory.getLogger(MybatisplusStudyApplicationTests.class);

    @Test
    public void testInert() {
        Student student = new Student("灰太狼", 37, "testInert-> " + TimeUtil.getCurTime());
        int result = studentMapper.insert(student);
        LOGGER.info("testInert,result->" + result);
    }


    @Test
    public void testUpdate() {
        Student student = new Student();
        student.setId(5);
        student.setAge(32);
        student.setName("张三");
        int result = studentMapper.updateById(student);
        LOGGER.info("testUpdate,result->" + result);
    }

    @Test
    public void testSelect() {
        // 参数是一个条件构造器Wrapper，这里先设置为null
        List<Student> studentList = studentMapper.selectList(null);
        for (Student s : studentList) {
            LOGGER.info(s.toString());
        }
    }

    @Test
    public void testOptimistic() {
        // 查询用户信息
        Student student = studentMapper.selectById(2);
        LOGGER.info(student.toString());
        // 更新用户信息
        student.setName("yhyy");
        student.setContent("test version");
        // 执行更新操作
        studentMapper.updateById(student);
    }


    @Test
    public void testOptimisticUnderMultiThread() {
        Student student = studentMapper.selectById(2);
        student.setName("yhyy11");
        student.setContent("test version11");

        Student student2 = studentMapper.selectById(2);
        student2.setName("yhyy22");
        student2.setContent("test version22");
        studentMapper.updateById(student2);

        studentMapper.updateById(student);
    }

    @Test
    public void testOptimisticUnderMultiThread2() {

        new Thread(() -> {
            Student student = studentMapper.selectById(6);
            student.setName("yhyy in AAA");
            student.setAge(student.getAge() + 10);
            student.setContent("test version in AAA");
            int i = studentMapper.updateById(student);
            LOGGER.info(Thread.currentThread().getName() + "->" + i);
        }, "A").start();

        new Thread(() -> {
            Student student = studentMapper.selectById(6);
            student.setName("yhyy in BBB");
            student.setAge(student.getAge() - 3);
            student.setContent("test version in BBB");
            int j = studentMapper.updateById(student);
            LOGGER.info(Thread.currentThread().getName() + "->" + j);

        }, "B").start();

        Student student = studentMapper.selectById(6);
        student.setName("yhyy in main");
        student.setAge(student.getAge() + 80);
        student.setContent("test version in main");
        int k = studentMapper.updateById(student);
        LOGGER.info(Thread.currentThread().getName() + "->" + k);
    }

    @Test
    public void testPage() {
        // 设置当前页和页面大小
        Page<Student> page = new Page<>(1, 3);
        // queryWrapper参数与高级查询有关
        studentMapper.selectPage(page, null);
        for (Student s : page.getRecords()) {
            LOGGER.info(s.toString());
        }
        LOGGER.info("page.getTotal()->" + page.getTotal());
    }

}
