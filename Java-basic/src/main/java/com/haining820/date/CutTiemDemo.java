package com.haining820.date;

import org.springframework.beans.BeanUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-10-20
 * Time: 10:55
 */
@Slf4j
public class CutTiemDemo {

    public static void main(String[] args) {


//        long time1 = new Date().getTime();
//
//        DateTime dateTime = new DateTime().minusDays(1);
//        long time2 = dateTime.toDate().getTime();
//        System.out.println(time1);
//        System.out.println(time2);
//        System.out.println(time1 - time2);

//        Map<String, Object> map = Maps.newHashMap();
//        map.put("label", "v1");
//        map.put("dropdownText", "v2");
//        map.put("btnText", "v3");
//        map.put("jumpUrl", "v4");
//        map.put("productNo", "v5");
//        map.put("applyTime", "v6");
//        map.put("marketingCode", "v7");
//        System.out.println(map);
//
//        DropDownBoxResp dropDownBoxResp = new DropDownBoxResp();
//
//        System.out.println(dropDownBoxResp);
//
//        BeanUtils.copyProperties(map.entrySet(),dropDownBoxResp);
//        System.out.println(dropDownBoxResp);

        Object s = null;
        String ss = (String) s;
        String sss = String.valueOf(s);
        System.out.println(ss);
        System.out.println(sss);

//        Date date = new Date();
//        System.out.println(date);
//        System.out.println(date.getTime());

//        Date date2 = null;
//        System.out.println(date2);
//        System.out.println(date2.getTime());


    }

}
