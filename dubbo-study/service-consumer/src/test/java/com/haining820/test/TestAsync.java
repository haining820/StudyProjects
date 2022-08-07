package com.haining820.test;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-07
 * Time: 23:17
 */

import com.alibaba.dubbo.rpc.RpcContext;
import com.haining820.service.AsyncService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName TestAsync
 * @Description TODO
 */
public class TestAsync {

    private static Logger LOGGER = LoggerFactory.getLogger(TestAsync.class);

    @Test
    public void testAddNum1() throws ExecutionException, InterruptedException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("service-consumer.xml");
        AsyncService asyncService = (AsyncService) context.getBean("asyncService");
        long start = System.currentTimeMillis();
        // 进行加法计算
        asyncService.addNum(1, 1);
        LOGGER.info("->asyncService.addNum finish");
        Future<Object> addFuture = RpcContext.getContext().getFuture();
        LOGGER.info("->get addFuture finish");
        // 进行减法计算
        asyncService.subNum(3, 2);
        LOGGER.info("->asyncService.subNum finish");
        Future<Object> subFuture = RpcContext.getContext().getFuture();
        LOGGER.info("->get subFuture finish");
        int addRes = (int) addFuture.get();
        LOGGER.info("->get addRes");
        int subRes = (int) subFuture.get();
        long end = System.currentTimeMillis();
        LOGGER.info("->get subRes");
        LOGGER.info("addRes->" + addRes);
        LOGGER.info("subRes->" + subRes);
        LOGGER.info("use time->" + (end - start));
        // 阻塞，读取一个字符（按任意键退出）
        System.in.read();
    }

}
