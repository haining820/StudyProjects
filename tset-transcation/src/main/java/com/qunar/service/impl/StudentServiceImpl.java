package com.qunar.service.impl;

import com.qunar.mapper.StudentMapper;
import com.qunar.pojo.Student;
import com.qunar.service.StudentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description: Service实现类，对事务进行测试
 * User: hn.yu
 * Date: 2022-07-30
 * Time: 18:03
 */
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Resource(name = "transactionTemplate")
    private TransactionTemplate myTransaction;

    @Resource(name = "sqlSessionFactory")
    private SqlSessionFactory mySqlSessionFactory;

    /**
     * 在这里重写的doInTransactionWithoutResult方法中的增加和删除被组合在一起作为一组事务，是一个整体；
     * 先增加后删除，如果删除失败了，会回滚，之前的增加也会无效。
     */
    @Override
    public void selectStudent() {
        SqlSession sqlSession = mySqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        myTransaction.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                // 获取当前时间，作为学生姓名，增加学生，测试事务
                String curTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                LOGGER.info("add->" + mapper.addStudent(new Student(curTime, 88)));
//                int i = 1 / 0;    // 报异常
                // 在StudentMapper.xml中的删除语句中where故意写成were，删除一定会失败，会报异常
                LOGGER.info("delete->" + mapper.deleteStudent(500));
                List<Student> list = mapper.selectStudent();
                LOGGER.info(list.toString());
            }
        });
    }
}