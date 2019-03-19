package com.hades.example.android.lib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

    /**
     * System.currentTimeMillis()
     * https://blog.csdn.net/weixin_41926301/article/details/80319375
     * https://www.cnblogs.com/denyungap/p/7525449/html
     * https://www.cnblogs.com/dengyungao/p/7525449.html
     */
    public void compareDate() {
        compareDate("2019-03-07 10:08:00:316", "2019-03-07 11:18:47:081");
    }

    // t1 = "2019-03-07 10:08:00:316"
    // t2 = "2019-03-07 11:18:47:081"
    public void compareDate(String start, String end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        long t1 = 0;
        try {
            t1 = sdf.parse("2019-03-07 10:08:00:316").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long t2 = System.currentTimeMillis();
        try {
            t2 = sdf.parse("2019-03-07 11:18:47:081").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        compareDate(t1, t2);
    }

    public String compareDate(long start, long end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(sdf.format(start) + "," + sdf.format(end));

        long t3 = end - start;
        long ms_hour = 60 * 60 * 1000;
        long ms_minute = 60 * 1000;
        long ms_s = 1000;

        int h = (int) (t3 / ms_hour);
        t3 = t3 - h * ms_hour;

        int m = (int) (t3 / ms_minute);
        t3 = t3 - m * ms_minute;

        int s = (int) (t3 / ms_s);
        t3 = t3 - s * ms_s;

        String duringTime = h + "h:" + m + "m:" + s+"s:" + t3  + "ms";
        System.out.println(duringTime);
        return duringTime;
    }
}
