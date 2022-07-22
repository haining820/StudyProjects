package qunar.p1;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-19
 * Time: 10:07
 */

import org.apache.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName FutureTest1
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-19 10:07
 */
public class FutureTest implements Callable<Integer> {

    private static Logger logger = Logger.getLogger(FutureTest.class);

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 10; i++) {
            logger.info(i);
        }
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTest futureTest = new FutureTest();
        FutureTask futureTask = new FutureTask(futureTest);
        new Thread(futureTask).start();
        int i = (int) futureTask.get();
        logger.info("futureTask1.get()->" + i);
    }
}
