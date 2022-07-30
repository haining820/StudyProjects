package com.qunar.futuretask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: yuhaining
 * Date: 2022-07-24
 * Time: 22:16
 */
public class FuTaskCallable implements Callable {

    @Override
    public Object call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Task is running...");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd hh:mm:ss");
        String format = sdf.format(date);
        return format;
    }
}
