package com.qunar.service.impl;

import com.qunar.mapper.StudentMapper;
import com.qunar.pojo.Student;
import com.qunar.service.StudentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: yuhaining
 * Date: 2022-07-30
 * Time: 18:03
 */
@Service
public class StudentServiceImpl implements StudentService {

    Logger LOGGER = Logger.getLogger(StudentServiceImpl.class);

    private TransactionTemplate transactionTemplate;

    private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession = null;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void selectStudent() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                List<Student> list = mapper.selectStudent();
                LOGGER.info("add->" + mapper.addStudent(new Student("trans888", 888)));
                LOGGER.info("add->" + mapper.addStudent(new Student("trans999", 999)));
                LOGGER.info("add->" + mapper.addStudent(new Student("dduu55", 123)));
                int i = 1 / 0;
                LOGGER.info("delete->" + mapper.deleteStudent(500));
                LOGGER.info(list.toString());
            }
        });
    }
}
