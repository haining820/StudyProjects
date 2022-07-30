package com.qunar.mapper;
import com.qunar.pojo.Student;

import java.util.List;

public interface StudentMapper {

    public List<Student> selectStudent();

    public int addStudent(Student student);

    public int deleteStudent(int id);


}
