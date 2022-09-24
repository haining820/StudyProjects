package com.haining820.springbootstudy.dao;

import com.haining820.springbootstudy.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentDao {

    List<Student> selectStudent();

    int addStudent(Student student);

    int deleteStudent(int id);

}
