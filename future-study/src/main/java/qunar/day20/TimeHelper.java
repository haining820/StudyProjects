package qunar.day20;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-20
 * Time: 13:18
 */

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName TimeHelper
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-20 13:18
 */

@Component
public class TimeHelper implements ITimeHelper{

    /**
     * @Description: 该方法构造一个超时之后会返回异常的future
     * @User: hn.yu
     * @Date: 2022/7/20 13:23
     * @Param: [timeout, timeUnit]
     * @Return: java.util.concurrent.CompletableFuture<T>
     **/

    @Override
    public CompletableFuture<String> fastFail(long timeout, TimeUnit timeUnit) {
        // 承载超时异常的future
        final CompletableFuture<String> future = new CompletableFuture<>();
        SCHEDULED_EXECUTOR.schedule(() -> {
            final TimeoutException ex = new TimeoutException("TimeoutException: " + timeout);
            return future.completeExceptionally(ex);
        }, timeout, timeUnit);
        return future;
    }

    static ScheduledThreadPoolExecutor SCHEDULED_EXECUTOR;

    static {
        SCHEDULED_EXECUTOR = new ScheduledThreadPoolExecutor(1, new MyThreadFactory());
        SCHEDULED_EXECUTOR.setRemoveOnCancelPolicy(true);
    }

    /*
     * ThreadFactory是一个接口类，也就是我们经常说的线程工厂，只有一个方法，可以用于创建线程
     * 默认情况下，ThreadPoolExecutor构造器传入的ThreadFactory参数是Executors类中的defaultThreadFactory()
     * 在这里构造ScheduledThreadPoolExecutor选择进行自定义，将线程池中的线程设置为守护线程
     * 如果主线程中的线程没有延时，成功运行，线程池中的线程也能自动结束
     **/
    static final class MyThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);  // 设置该线程为守护线程
            t.setName("CompletableFutureDelayScheduler");
            return t;
        }
    }



}
