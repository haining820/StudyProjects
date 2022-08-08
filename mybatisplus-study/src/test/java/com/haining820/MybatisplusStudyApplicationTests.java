package com.haining820;

import com.haining820.entity.Employee;
import com.haining820.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisplusStudyApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    private static Logger LOGGER = LoggerFactory.getLogger(MybatisplusStudyApplicationTests.class);

    @Test
    void contextLoads() {
        // 参数是一个条件构造器，这里先设置为null
        List<Employee> employeeList = employeeMapper.selectList(null);
        for (Employee e : employeeList) {
            LOGGER.info(e.toString());
        }

    }

    @Test
    public void testInert() {
        Employee employee = new Employee(8, "喜羊羊", "13866669999", "北京", 1, 1);
        int result = employeeMapper.insert(employee);
        LOGGER.info("testInert,result->" + result);
    }

    @Test
    public void testUpdate(){
        Employee employee = new Employee();
        employee.setId(8);
        employee.setName("懒羊羊");
        int result = employeeMapper.updateById(employee);
        LOGGER.info("testUpdate,result->" + result);
    }

}
