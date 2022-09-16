package com.haining820.mapper;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-01
 * Time: 12:01
 */

import com.haining820.pojo.Teacher;

import java.util.List;

/**
 * @ClassName TeacherMapper
 * @Description 对数据库中的teacher表进行CURD
 */
public interface TeacherMapper {

    List<Teacher> selectTeacher();

    int addTeacher(Teacher teacher);

    int deleteTeacher(int id);

}
