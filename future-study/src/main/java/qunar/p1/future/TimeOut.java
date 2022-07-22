package qunar.p1.future;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-19
 * Time: 16:34
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 描述： 演示get的超时方法，需要注意超时后处理，调用future.cancel(),
 * 演示cancel传入true和false的区别，代表是否中断正在执行的任务
 */
public class TimeOut {

    private static final Ad DEFAULT_AD = new Ad("无网络时候的默认广告");

    private static final ExecutorService exec = Executors.newFixedThreadPool(10);

    static class Ad{
        String name;
        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                System.out.println("sleep 期间被中断了");
                return new Ad("被中断时候的默认广告");
            }
            return new Ad("挖掘机技术哪家强？");
        }
    }

    public void printAd(){
        Future<Ad> future = exec.submit(new FetchAdTask());
        Ad ad;
        try {
            ad = future.get(4000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断时候的默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("异常时候的默认广告");
        } catch (TimeoutException e) {
            ad = new Ad("超时时候的默认广告");
            System.out.println("超时，为获取到广告");
            boolean cancel = future.cancel(false);
            System.out.println("cancel的结果："+cancel);
        }
        exec.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        TimeOut timeOut = new TimeOut();
        timeOut.printAd();
    }
}
