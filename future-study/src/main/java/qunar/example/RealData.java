package qunar.example;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-07-19
 * Time: 10:57
 */

/**
 * @ClassName RealData
 * @Description TODO
 * @User hn.yu
 * @Date 2022-07-19 10:57
 */
public class RealData implements Data {
    protected final String result;
    public RealData(String para) {
        StringBuffer sb=new StringBuffer();
        //假设这里很慢很慢，构造RealData不是一个容易的事
        result =sb.toString();
    }
    public String getResult() {
        return result;
    }
}


