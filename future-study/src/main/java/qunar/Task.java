package qunar;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-22
 * Time: 11:20
 */

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Task
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-22 11:20
 */
public class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("Task->"+Thread.currentThread().getName());

        TimeUnit.SECONDS.sleep(1);
//        int a = 1 / 0;    // 异常
        return "task done";
    }
}
