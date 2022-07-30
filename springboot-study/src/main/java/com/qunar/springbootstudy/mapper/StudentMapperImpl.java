package com.qunar.springbootstudy.mapper;

import com.qunar.springbootstudy.pojo.Student;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import java.util.List;

public class StudentMapperImpl extends SqlSessionDaoSupport implements StudentMapper {

    @Override
    public List<Student> selectStudent() {
        StudentMapper mapper = getSqlSession().getMapper(StudentMapper.class);
        Student student = new Student("testInTrans88", 88);
        System.out.println(">>>>>>>>>>>>>>>>>>>" + mapper.addStudent(student));
        System.out.println(">>>>>>>>>>>>>>>>>>>" + mapper.deleteStudent(100));
        return mapper.selectStudent();
    }

    @Override
    public int addStudent(Student student) {
        return getSqlSession().getMapper(StudentMapper.class).addStudent(student);
    }

    @Override
    public int deleteStudent(int id) {
        return getSqlSession().getMapper(StudentMapper.class).deleteStudent(id);
    }
}
