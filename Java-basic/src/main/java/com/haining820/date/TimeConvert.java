package com.haining820.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-10-26
 * Time: 11:23
 */
public class TimeConvert {

    public static void main(String[] args) {

        Long timeStamp = 1667368999668l;

        Date date = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));


        String uid = "184446387";
        System.out.println(Math.abs(uid.hashCode() % 1000));
    }

}
