package com.haining820.futureconvert;
/**
 * Created with IntelliJ IDEA
 * Description: 获得当前系统时间，用于测试数据的更新。
 * User: hn.yu
 * Date: 2022-08-03
 * Time: 19:08
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTools {

    /**
     * 获取当前时间
     */
    public static String getCurTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 睡一会
     */
    public static void sleepMillis(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
