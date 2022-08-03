package com.qunar.utils;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-03
 * Time: 19:08
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName GetCurTime
 * @Description TODO
 */
public class GetCurTime {

    public static String get() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
