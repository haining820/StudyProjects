package com.haining820.mapper;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-08
 * Time: 10:41
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haining820.entity.Student;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentMapper extends BaseMapper<Student> {

}
