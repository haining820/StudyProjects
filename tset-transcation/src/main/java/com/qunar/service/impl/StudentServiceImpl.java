package com.qunar.service.impl;

import com.qunar.mapper.StudentMapper;
import com.qunar.pojo.Student;
import com.qunar.service.StudentService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    @Override
    public void selectStudent() {

    }

    /**
     * 不添加事务控制，后面的delete失败了但是前边的add依然可以成功
     */
    public void testTran() {
        SqlSession sqlSession = mySqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // 获取当前时间，作为学生姓名，增加学生，测试事务
        LOGGER.info("test4: add->" + mapper.addStudent(new Student(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 88)));
        // 在StudentMapper.xml中的删除语句中where故意写成were，删除一定会失败，会报异常
        LOGGER.info("test4: delete->" + mapper.deleteStudent(500));
        List<Student> list = mapper.selectStudent();
        LOGGER.info(list.toString());
    }


    /**
     * 测试普通的无返回值的事务
     * 在这里重写的doInTransactionWithoutResult方法中的add和delete被组合在一起作为一组事务，是一个整体；
     * 先增加后删除，如果删除失败了，会回滚，之前的增加也会无效。
     */
    public void testTranWithoutResult() {
        SqlSession sqlSession = mySqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        myTransaction.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                // 获取当前时间，作为学生姓名，增加学生，测试事务
                LOGGER.info("test1: add->" + mapper.addStudent(new Student(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 88)));
//                int i = 1 / 0;    // 人为的创建异常
                // 在StudentMapper.xml中的删除语句中where故意写成were，删除一定会失败，会报异常
//                LOGGER.info("test1: delete->" + mapper.deleteStudent(500));
                List<Student> list = mapper.selectStudent();
                LOGGER.info(list.toString());
            }
        });
    }

    /**
     * 测试对业务进行手动回滚
     */
    public void testTranSetRollback() {
        SqlSession sqlSession = mySqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        myTransaction.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                LOGGER.info("test2: add->" + mapper.addStudent(new Student(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 77)));
                // 对特殊的业务设置手动回滚
                int random = new Random().nextInt();
                if (random % 2 == 0) {
                    LOGGER.error("test2: random->" + random + ", rollback");
                    status.setRollbackOnly();
                }
            }
        });
    }

    /**
     * 测试有返回值的事务
     */
    public void testTranWithResult() {
        SqlSession sqlSession = mySqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // 使用lambda表达式简化
        Boolean flag = myTransaction.execute((status) -> {
            try {
                LOGGER.info("test3: add->" + mapper.addStudent(new Student(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 66)));
                int i = 1 / 0;    // 人为的创建异常
            } catch (Exception e) {
                // 手动设置回滚，返回false
                status.setRollbackOnly();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        });
        if (!flag) {
            LOGGER.error("test3: transaction failed, rollback.");
        }
    }


}