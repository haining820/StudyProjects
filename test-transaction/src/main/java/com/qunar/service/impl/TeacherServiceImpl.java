package com.qunar.service.impl;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-01
 * Time: 12:06
 */

import com.qunar.mapper.TeacherMapper;
import com.qunar.pojo.Teacher;
import com.qunar.service.TeacherService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TeacherServiceImpl
 * @Description 测试声明式事务
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    private static Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Resource(name = "sqlSessionFactory")
    private SqlSessionFactory mySqlSessionFactory;

    @Override
    public void selectTeacher() {

    }


    /**
     * delete语句有问题，无法正常删除，会抛出异常，此时前边的add操作也不会成功
     * 使用声明式事务不用修改代码即可实现事务
     */
    @Override
    public void testAopTran() {
        SqlSession session = mySqlSessionFactory.openSession();
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        Teacher teacher = new Teacher("王老师", 35, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        LOGGER.info("testAopTran: add->" + mapper.addTeacher(teacher));
        LOGGER.info("testAopTran: delete->" + mapper.deleteTeacher(1));
    }
}
