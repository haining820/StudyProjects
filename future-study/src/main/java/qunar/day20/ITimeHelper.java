package qunar.day20;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-20
 * Time: 13:28
 */
public interface ITimeHelper {

    CompletableFuture<String> fastFail(long timeout, TimeUnit timeUnit);

}
