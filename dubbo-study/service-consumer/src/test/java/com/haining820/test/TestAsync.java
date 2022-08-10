package com.haining820.test;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-07
 * Time: 23:17
 */


import com.alibaba.dubbo.remoting.exchange.ResponseCallback;
import com.alibaba.dubbo.remoting.exchange.ResponseFuture;
// dubbo 2.6
//import com.alibaba.dubbo.rpc.RpcContext;
//import com.alibaba.dubbo.rpc.protocol.dubbo.FutureAdapter;
import com.haining820.service.AsyncContextAsyncService;
import com.haining820.service.DefaultAsyncService;
import com.haining820.service.NormalAsyncService;
import com.haining820.service.RpcContextAsyncService;
import com.haining820.service.CompletableFutureAsyncService;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.protocol.dubbo.FutureAdapter;
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

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("service-consumer.xml");


    /**
     * 测试2.6.x版本之前的异步实现方式
     */
    @Test
    public void testNormal1() throws ExecutionException, InterruptedException, IOException {
        NormalAsyncService service = (NormalAsyncService) context.getBean("normalAsyncService");
        service.sayHelloAsync("normal async service");
        Future<String> helloFuture = RpcContext.getContext().getFuture();
        LOGGER.info("testNormal1->" + helloFuture.get());
        System.in.read();
    }

/*    @Test
    public void testNormal2() throws ExecutionException, InterruptedException, IOException {
        NormalAsyncService service = (NormalAsyncService) context.getBean("normalAsyncService");
        service.sayHelloAsync("normal async service");
        // 在dubbo2.7中无法使用，ResponseFuture、ResponseCallBack会被标记为已废弃的方法
        ResponseFuture helloFuture = ((FutureAdapter) RpcContext.getContext().getFuture()).getFuture();
        helloFuture.setCallback(new ResponseCallback() {
            @Override
            public void done(Object o) {
                // testNormal2->RpcResult [result=normal async service: hello,normal async service, exception=null]
                LOGGER.info("testNormal2->" + o.toString());
            }
            @Override
            public void caught(Throwable exception) {
                exception.printStackTrace();
            }
        });
        System.in.read();

    }*/

    /**
     * 测试dubbo的异步实现方式：CompletableFuture
     */
    @Test
    public void testSayHelloCompletableFuture() throws ExecutionException, InterruptedException, IOException {
        CompletableFutureAsyncService service = (CompletableFutureAsyncService) context.getBean("cfAsyncService");
        CompletableFuture<String> completableFuture = service.sayHelloAsync("cfAsyncService: hello dubbo");
        LOGGER.info("testSayHello->" + completableFuture.get());
        System.in.read();
    }

    /**
     * 测试dubbo的异步实现方式：添加接口的默认方法
     */
    @Test
    public void testDefault() throws ExecutionException, InterruptedException, IOException {
        DefaultAsyncService service = (DefaultAsyncService) context.getBean("defaultAsyncService");
        CompletableFuture<String> future = service.sayHello("async call request", true);
        LOGGER.info("async call ret :" + future.get());
        System.in.read();
    }

    /**
     * 测试dubbo的异步实现方式：AsyncContext
     */
    @Test
    public void testSayHelloAsyncContext() throws IOException {
        AsyncContextAsyncService service = (AsyncContextAsyncService) context.getBean("acAsyncService");
        // 不用CompletableFuture也能返回执行结果
        LOGGER.info("testSayHelloAsyncContext->" + service.sayHelloAsync("async call request"));
        System.in.read();
    }

    /**
     * 测试dubbo的异步实现方式：RpcContext
     */
    @Test
    public void testSayHelloRpcContext() throws ExecutionException, InterruptedException, IOException {
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

        System.in.read();
    }
}