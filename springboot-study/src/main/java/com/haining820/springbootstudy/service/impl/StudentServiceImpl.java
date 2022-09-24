package com.haining820.springbootstudy.service.impl;

import com.haining820.springbootstudy.dao.StudentDao;
import com.haining820.springbootstudy.entity.Student;
import com.haining820.springbootstudy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-24
 * Time: 15:07
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> selectStudent() {
        return studentDao.selectStudent();
    }

    @Override
    public int addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public int deleteStudent(int id) {
        return studentDao.deleteStudent(id);
    }
}
