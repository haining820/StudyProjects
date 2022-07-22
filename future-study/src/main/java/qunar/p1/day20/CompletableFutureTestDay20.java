package qunar.p1.day20;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-20
 * Time: 10:10
 */

import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CompletableFutureTestDay20
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-20 10:10
 */

public class CompletableFutureTestDay20 {

    private static ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);

    private static Logger LOGGER = Logger.getLogger(CompletableFutureTestDay20.class);

    private static void timeoutProcess(CompletableFuture<String> completableFuture){
        int timeoutSeconds = 10;
        Uninterruptibles.sleepUninterruptibly(timeoutSeconds, TimeUnit.SECONDS);
        completableFuture.complete("timeout exception");
    }

    public static void testComFuture() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        EXECUTOR.execute(() -> {
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
            completableFuture.complete("yhn");
        });
        completableFuture.thenAccept(s -> LOGGER.info("s->" + s));
        timeoutProcess(completableFuture);
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);

    }

    public static void main(String[] args) {
        testComFuture();
    }

}
