package com.qunar.springbootstudy.mapper;

import com.qunar.springbootstudy.pojo.Student;

import java.util.List;

public interface StudentMapper {

    public List<Student> selectStudent();

    public int addStudent(Student student);

    public int deleteStudent(int id);


}
