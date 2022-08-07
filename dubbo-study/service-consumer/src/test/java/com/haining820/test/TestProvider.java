package com.haining820.test;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-07
 * Time: 14:14
 */

import com.haining820.controller.UserController;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @ClassName TestProvider
 * @Description: TODO
 */
public class TestProvider {

    private static Logger LOGGER = LoggerFactory.getLogger(TestProvider.class);

    @Test
    public void testGetById() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("service-consumer.xml");
        UserController userController = (UserController) context.getBean("userController");
        String user = userController.getUser();
        LOGGER.info("TestProvider.testGetById->" + user);
        // 阻塞，读取一个字符（按任意键退出）
        System.in.read();
    }

}
