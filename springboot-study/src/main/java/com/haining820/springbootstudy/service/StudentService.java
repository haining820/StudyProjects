package com.haining820.springbootstudy.service;

import com.haining820.springbootstudy.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> selectStudent();

    int addStudent(Student student);

    int deleteStudent(int id);
}
