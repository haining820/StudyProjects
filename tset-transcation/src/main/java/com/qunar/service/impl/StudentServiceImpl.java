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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

    @Resource(name = "studentMapper")
    private StudentMapper studentMapper;

    @Override
    public int myUpdateStudent(String name) {
        int i = studentMapper.updateStudent(100, name);
        return i;
    }

    @Override
    public int myAddStudent() {
        int i = studentMapper.addStudent(new Student("myAddStudent->" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 11));
        LOGGER.info("myAddStudent: add->" + i);
        return i;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void testTranNever() {
        Student student = new Student("testTranNever->" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 66);
        studentMapper.addStudent(student);
        List<Student> list = studentMapper.selectStudent();
        LOGGER.info(list.toString());
    }

    @Override
    @Transactional
    public int testTranRequired(String name) {
        Student student = new Student(name, 66);
        int addRes = studentMapper.addStudent(student);
        if (addRes == 1) {
            LOGGER.info("testTranRequired: add success.");
            throw new RuntimeException("testTranRequired: test transaction roll back");
        }
        return addRes;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public int testTranNested(String name) {
        Student student = new Student(name, 66);
        int addRes = studentMapper.addStudent(student);
        if (addRes == 1) {
            LOGGER.info("testTranNested: add success.");
            throw new RuntimeException("testTranNested: test transaction roll back");
        }
        return addRes;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public int testTranNested2(String name) {
        Student student = new Student(name, 66);
        int addRes = studentMapper.addStudent(student);
        LOGGER.info("testTranNested2: add success.");
        return addRes;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int testTranRequiresNew(String name) {
        Student student = new Student(name, 66);
        int addRes = studentMapper.addStudent(student);
        LOGGER.info("testRequiresNew: add success.");
        return addRes;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int testTranRequiresNew2(int id, String name) {
        return studentMapper.updateStudent(id, name);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int testTranNotSupported(int id, String name) {
        int i = studentMapper.updateStudent(id, name);
        if (i == 1) {
            throw new RuntimeException("testTranNotSupported roll back");
        }
        return i;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int testTranNotSupported2(int id, String name) {
        return studentMapper.updateStudent(id, name);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public int testTranMandatory(int id, String name) {
        return studentMapper.updateStudent(id,name);
    }

    /**
     * 不添加事务控制，后面的delete失败了但是前边的add依然可以成功
     */
    public void testTranFail() {
        // 获取当前时间，作为学生姓名，增加学生，测试事务
        LOGGER.info("testTranFail: add->" + studentMapper.addStudent(new Student(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 11)));
        // 在StudentMapper.xml中的删除语句中where故意写成were，删除一定会失败，会报异常
        LOGGER.info("testTranFail: delete->" + studentMapper.deleteStudent(500));
        List<Student> list = studentMapper.selectStudent();
        LOGGER.info(list.toString());
    }


    /**
     * 测试普通的无返回值的事务
     * 在这里重写的doInTransactionWithoutResult方法中的add和delete被组合在一起作为一组事务，是一个整体；
     * 先增加后删除，如果删除失败了，会回滚，之前的增加也会无效。
     */
    public void testTranWithoutResult() {
        myTransaction.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                // 获取当前时间，作为学生姓名，增加学生，测试事务
                LOGGER.info("testTranWithoutResult: add->" + studentMapper.addStudent(new Student(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 22)));
//                int i = 1 / 0;    // 人为的创建异常
                // 在StudentMapper.xml中的删除语句中where故意写成were，删除一定会失败，会报异常
//                LOGGER.info("test1: delete->" + mapper.deleteStudent(500));
                List<Student> list = studentMapper.selectStudent();
                LOGGER.info(list.toString());
            }
        });
    }

    /**
     * 测试对业务进行手动回滚
     */
    public void testTranSetRollback() {
        myTransaction.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                LOGGER.info("test2: add->" + studentMapper.addStudent(new Student(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 33)));
                // 对特殊的业务设置手动回滚
                int random = new Random().nextInt();
                if (random % 2 == 0) {
                    LOGGER.error("testTranSetRollback: random->" + random + ", rollback");
                    status.setRollbackOnly();
                }
            }
        });
    }

    /**
     * 测试有返回值的事务
     */
    public void testTranWithResult() {
        // 使用lambda表达式简化
        Boolean flag = myTransaction.execute((status) -> {
            try {
                LOGGER.info("test3: add->" + studentMapper.addStudent(new Student(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 44)));
                int i = 1 / 0;    // 人为的创建异常
            } catch (Exception e) {
                // 手动设置回滚，返回false
                status.setRollbackOnly();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        });
        if (!flag) {
            LOGGER.error("testTranWithResult: task failed, rollback.");
        }
    }

    /**
     * 使用@Transactional注解，默认等级REQUIRED，新建一个事务，也可以实现之前类似testTranWithoutResult的功能
     */
    @Transactional
    public void testAnnoTran() {
        LOGGER.info("testAnnoTran: add->" + studentMapper.addStudent(new Student(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 55)));
        LOGGER.info("testTranFail: delete->" + studentMapper.deleteStudent(500));
    }


    /**
     * 新增用户，该方法设置为Propagation.NEVER，以无事务的方式执行，如果当前有事务则报错
     */
    @Transactional(propagation = Propagation.NEVER)
    public void testAnnoTranNever() {
        LOGGER.info("testAnnoTran: add->" + studentMapper.addStudent(new Student("never->" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 66)));
    }

    /**
     * 同类之间的方法调用：有事务调用无事务
     * testTestAnnoTranNever默认等级REQUIRED
     * testTestAnnoTranNever调用testAnnoTranNever
     * testAnnoTranNever以无事务的方式执行，但是testTestAnnoTranNever会创建事务
     * 结果：
     * 如果使用@Transactional方法里嵌套调用的是同一个类的方法，spring代理会忽略嵌套方法的@Transactional配置。
     * 可以正常运行并插入数据，testAnnoTranNever的Propagation.NEVER被忽略。
     */
    @Transactional
    public void testTestAnnoTranNever() {
        testAnnoTranNever();
    }

    /**
     * insertStudent默认等级REQUIRED，会创建事务
     *
     * @return
     */
    @Transactional
    public int insertStudent() {
        Student student = new Student("insertStudent->" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 66);
        int insertCount = studentMapper.addStudent(student);
        // 插入成功
        if (insertCount == 1) {
            LOGGER.info("insertStudent: insert success");
            throw new RuntimeException("test transaction roll back");
        }
        return insertCount;
    }

    /**
     * 同类之间的方法调用：无事务调用有事务
     * insertStudent默认等级REQUIRED，创建事务
     * testInsertStudent调用insertStudent
     * insertStudent在插入学生时会创建事务，但是调用insertStudent的testInsertStudent以无事务的方式执行
     * 结果：
     * 抛出异常：java.lang.RuntimeException: test transaction roll back
     * 程序抛出异常但是数据插入成功
     */
    @Transactional(propagation = Propagation.NEVER)
    public void testInsertStudent() {
        insertStudent();
    }


}