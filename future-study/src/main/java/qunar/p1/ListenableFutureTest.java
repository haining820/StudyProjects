package qunar.p1;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-19
 * Time: 15:54
 */

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ListenableFutureTest
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-19 15:54
 */
public class ListenableFutureTest {

    public static void main(String[] args) {
        testListenFuture();
    }

    public static void testListenFuture() {
        System.out.println("主任务执行完，开始异步执行副任务1.....");
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> future = pool.submit(new Task());
        // 为任务绑定回调接口
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("成功,结果是:" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("出错,业务回滚或补偿");
            }
        });
        System.out.println("副本任务启动,回归主任务线，主业务正常返回2.....");
    }
}

class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
//        int a = 1 / 0;    // 异常
        return "task done";
    }
}