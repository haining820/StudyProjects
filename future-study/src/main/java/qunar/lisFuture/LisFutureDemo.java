package qunar.lisFuture;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-22
 * Time: 17:39
 */

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;
import qunar.Task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName LisFutureDemo
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-22 17:39
 */
public class LisFutureDemo {


    @Test
    public void lisFuture() throws ExecutionException, InterruptedException {
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> lf = pool.submit(new Task());
        System.out.println(lf.get());
        lf.addListener(() -> {
            System.out.println("finish lf");
        }, Executors.newFixedThreadPool(5));
    }

}
