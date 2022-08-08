package com.haining820.test;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-07
 * Time: 23:17
 */


import com.haining820.service.AsyncContextAsyncService;
import com.haining820.service.RpcContextAsyncService;
import com.haining820.service.CompletableFutureAsyncService;
import org.apache.dubbo.rpc.RpcContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName TestAsync
 * @Description TODO
 */
public class TestAsync {

    private static Logger LOGGER = LoggerFactory.getLogger(TestAsync.class);

    /**
     * 测试dubbo的异步实现方式：CompletableFuture
     */
    @Test
    public void testSayHelloCompletableFuture() throws ExecutionException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("service-consumer.xml");
        CompletableFutureAsyncService service = (CompletableFutureAsyncService) context.getBean("cfAsyncService");
        CompletableFuture<String> completableFuture = service.sayHelloAsync("cfAsyncService: hello dubbo");
        LOGGER.info("testSayHello->" + completableFuture.get());
    }

    /**
     * 测试dubbo的异步实现方式：RpcContext
     */
    @Test
    public void testSayHelloRpcContext() throws ExecutionException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("service-consumer.xml");
        RpcContextAsyncService service = (RpcContextAsyncService) context.getBean("rcAsyncService");

        service.sayHelloAsync("RpcContextDubbo");
        CompletableFuture<String> helloFuture = RpcContext.getContext().getCompletableFuture();
        helloFuture.whenComplete((retValue, exception) -> {
            if (exception == null) {
                LOGGER.info("return value: " + retValue);
            } else {
                exception.printStackTrace();
            }
        });

        CompletableFuture<String> f = org.apache.dubbo.rpc.RpcContext.getContext().asyncCall(() -> service.sayHelloAsync("async call request"));
        LOGGER.info("async call returned: " + f.get());

        org.apache.dubbo.rpc.RpcContext.getContext().asyncCall(() -> {
            service.sayHelloAsync("one way call request1");
        });
    }

    /**
     * 测试dubbo的异步实现方式：AsyncContext
     */
    @Test
    public void testSayHelloAsyncContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("service-consumer.xml");
        AsyncContextAsyncService service = (AsyncContextAsyncService) context.getBean("acAsyncService");

        // 调用直接返回CompletableFuture
        CompletableFuture<String> future = service.sayHelloAsync("async call request");
        // 增加回调
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("Response: " + v);
            }
        });
        // 早于结果输出
        System.out.println("Executed before response return.");
    }


}
