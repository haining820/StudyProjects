package com.haining820.service.impl;
/**
 * Created with IntelliJ IDEA
 * Description: 测试事务的传播特性
 * User: hn.yu
 * Date: 2022-08-03
 * Time: 16:07
 */

import com.haining820.mapper.StudentMapper;
import com.haining820.service.StudentService;
import com.haining820.service.TransactionalTestService;
import com.haining820.futureconvert.GetCurTime;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName TransactionalTestServiceImpl
 * @Description TODO
 */
@Service
public class TransactionalTestServiceImpl implements TransactionalTestService {

    private static Logger LOGGER = Logger.getLogger(TransactionalTestServiceImpl.class);


    @Resource
    StudentService studentService;

    @Resource(name = "studentMapper")
    private StudentMapper studentMapper;

    /**
     * SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
     * 事务的有无没有区别！
     */


    /**
     * 不同类之间的方法调用：REQUIRED调用NEVER
     * 会抛出异常：org.springframework.transaction.IllegalTransactionStateException:
     * Existing transaction found for transaction marked with propagation 'never'
     * <p>
     * NEVER不允许当前存在事务！
     */
    @Override
    @Transactional
    public void testTranNever() {
        studentService.testTranNever();
    }


    /**
     * 不同类之间的方法调用：REQUIRED调用REQUIRED
     * StudentService中的testTranRequired方法在添加成功后会抛出异常：java.lang.RuntimeException: testTranRequired: test transaction roll back
     * 在TransactionalTestService这边调用时会将其捕获，然后执行其他add方法，结果是两个方法都被回滚了
     * log输出内容如下，可以看到testTranRequired()和myAddStudent()都执行成功了，
     * [INFO ][2022-08-03 16:56:31][com.qunar.service.impl.StudentServiceImpl.testTranRequired(StudentServiceImpl.java:68)]
     * testTranRequired: add success.
     * [INFO ][2022-08-03 16:56:31][com.qunar.service.impl.StudentServiceImpl.myAddStudent(StudentServiceImpl.java:49)]
     * myAddStudent: add->1
     * 数据库中的显示表详情 AUTO_INCREMENT从143变成了145，但是表中并没有数据，说明被回滚了
     * testTranRequired()和myAddStudent()被看作是一个整体
     * <p>
     * REQUIRED的子事务会影响当前事务的提交、回滚！
     */
    @Override
    @Transactional
    public void testTranRequired() {
        String name = "testTranRequired->" + GetCurTime.get();
        // 捕获抛出的异常
        try {
            studentService.testTranRequired(name);
        } catch (Exception e) {
            // 不做处理
        }
        studentService.myAddStudent();
    }


    /**
     * NESTED情况（1）
     * 不同类之间的方法调用：REQUIRED调用NESTED
     * StudentService中的testTranRequired方法在添加成功后会抛出异常回滚，但由于事务类型是NESTED，所以该事务的回滚不会影响外部事务
     * 外层TransactionalTestService的testTranNested事务中的myAddStudent()会成功执行添加数据
     */
    @Override
    @Transactional
    public void testTranNested() {
        String name = "testTranNested->" + GetCurTime.get();
        // 捕获抛出的异常
        try {
            studentService.testTranNested(name);
        } catch (Exception e) {
            // 不做处理
        }
        studentService.myAddStudent();
    }

    /**
     * NESTED情况（2）
     * 不同类之间的方法调用：REQUIRED调用NESTED
     * StudentService中的testTranRequired方法在添加成功后不抛出异常，正常执行
     * 外层TransactionalTestService的testTranNested事务中的myAddStudent()添加成功后会抛出异常
     * log内容如下，同样说明两条数据都插入成功了：
     * [INFO ][2022-08-03 17:23:48][com.qunar.service.impl.StudentServiceImpl.testTranNested2(StudentServiceImpl.java:92)]
     * testTranNested2: add success.
     * [INFO ][2022-08-03 17:23:49][com.qunar.service.impl.StudentServiceImpl.myAddStudent(StudentServiceImpl.java:50)]
     * myAddStudent: add->1
     * 数据库中的显示表详情 AUTO_INCREMENT从158变成了160，但是表中并没有数据，说明被回滚了
     * <p>
     * NESTED子事务回滚不会影响当前事务的提交(catch回滚异常的情况下)，
     * 但是当前事务回滚会回滚子事务，只有当前事务提交成功了，子事务才会提交成功。
     */
    @Override
    @Transactional
    public void testTranNested2() {
        String name = "testTranNested2->" + GetCurTime.get();
        studentService.testTranNested2(name);
        int i = studentService.myAddStudent();
        if (i == 1) {
            throw new RuntimeException("testTranNested2: test transaction roll back");

        }
    }

    /**
     * REQUIRES_NEW情况（1）
     * 不同类之间的方法调用：REQUIRED调用REQUIRES_NEW
     * 抛出异常：RuntimeException: testRequiresNew: test transaction roll back，studentService.myAddStudent()回滚
     * 但是studentService.testTranRequiresNew(name)插入成功了
     */
    @Override
    @Transactional
    public void testTranRequiresNew() {
        String name = "testRequiresNew->" + GetCurTime.get();
        studentService.testTranRequiresNew(name);
        int i = studentService.myAddStudent();
        if (i == 1) {
            throw new RuntimeException("testRequiresNew: test transaction roll back");
        }
    }

    /**
     * REQUIRES_NEW情况（2）
     * 运行结果：在程序中两个方法操作的是数据库表中的同一行，程序会卡死
     * <p>
     * REQUIRES_NEW会启用一个新的事务，事务拥有完全独立的能力，它不依赖于当前事务，执行时会挂起当前事务，直到REQUIRES_NEW事务完成提交后才会提交当前事务
     * 如果当前事务与REQUIRES_NEW冲突，会存在锁竞争导致死锁。
     */
    @Override
    @Transactional
    public void testTranRequiresNew2() {
        String nameA = "testRequiresNew2->nameA->" + GetCurTime.get();
        String nameB = "testRequiresNew2->nameB->" + GetCurTime.get();
        // 在测试事务类中直接调用studentMapper中的updateStudent
        studentMapper.updateStudent(100, nameA);
        // 在测试事务类中直接调用studentService中的testTranRequiresNew2
        // 该方法为REQUIRES_NEW且实际上调用的也是studentMapper中的updateStudent
        studentService.testTranRequiresNew2(100, nameB);
        LOGGER.info("testTranRequiresNew2 finish!");
    }

    /**
     * 不同类之间的方法调用：REQUIRED调用NOT_SUPPORTED
     * RuntimeException: testTranNotSupported roll back
     * 程序会报异常但是数据依然会更新
     */
    @Override
    @Transactional
    public void testTranNotSupported() {
        String name = "testTranNotSupported->" + GetCurTime.get();
        studentService.testTranNotSupported(125, name);
    }

    /**
     * 运行结果：在程序中两个方法操作的是数据库表中的同一行，程序会卡死
     * NOT_SUPPORTED会挂起当前事务，并且NOT_SUPPORTED定义的方法内部不启用显式事务，
     * 如果NOT_SUPPORTED和当前事务存在锁竞争，会发生死锁。
     */
    @Override
    @Transactional
    public void testTranNotSupported2() {
        String nameA = "testTranNotSupported2->nameA->" + GetCurTime.get();
        String nameB = "testTranNotSupported2->nameB->" + GetCurTime.get();
        studentMapper.updateStudent(125, nameA);
        studentService.testTranNotSupported2(126, nameB);
        LOGGER.info("testTranNotSupported2 finish!");

    }


    /**
     * 不同类之间的方法调用：REQUIRED调用MANDATORY
     * 这里不加@Transactional注解使用事务的话，会报：
     * org.springframework.transaction.IllegalTransactionStateException:
     * No existing transaction found for transaction marked with propagation 'mandatory'
     * 加上注解之后会正常更新数据
     * <p>
     * MANDATORY必须包含在事务中，如果没有事务，则会抛出异常
     */
    @Override
//    @Transactional
    public void testTranMandatory() {
        studentService.testTranMandatory(150, "testTranMandatory->" + GetCurTime.get());
    }
}