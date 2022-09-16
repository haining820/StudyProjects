package com.haining820.mapper;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-31
 * Time: 22:40
 */

import com.haining820.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName StudentMapper
 * @Description: 对数据库中的student表进行CURD
 */
public interface StudentMapper {

    List<Student> selectStudent();

    int addStudent(Student student);

    int deleteStudent(int id);

    int updateStudent(@Param("id")int id, @Param("name") String name);

}
