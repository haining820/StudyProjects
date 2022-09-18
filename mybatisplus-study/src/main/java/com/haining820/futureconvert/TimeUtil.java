package com.haining820.futureconvert;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-13
 * Time: 15:14
 */

import org.joda.time.DateTime;


public class TimeUtil {

    private static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";

    public static String getCurTime(){
        return new DateTime().toString(PATTERN_STANDARD);

    }

}
