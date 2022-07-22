package qunar.example;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-19
 * Time: 10:57
 */

/**
 * @ClassName Client
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-19 10:57
 */
public class Client {
    //这是一个异步方法，返回的Data接口是一个Future
    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        new Thread() {
            public void run() {
                // RealData的构建很慢，所以在单独的线程中进行
                RealData realdata = new RealData(queryStr);
                //setRealData()的时候会notify()等待在这个future上的对象
                future.setRealData(realdata);
            }
        }.start();
        // FutureData会被立即返回，不会等待RealData被构造完
        return future;
    }
}

