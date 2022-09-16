package com.haining820.springbootstudy.mapper;

import com.haining820.springbootstudy.pojo.Student;

import java.util.List;

public interface StudentMapper {

    public List<Student> selectStudent();

    public int addStudent(Student student);

    public int deleteStudent(int id);


}
