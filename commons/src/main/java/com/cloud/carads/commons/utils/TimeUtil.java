package com.cloud.carads.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by barrie on 2017/5/2.
 */
public class TimeUtil {
    public static Date getDate(Date date, int intervalYear, int intervalMonth, int intervalDay) {
        Date d = new Date(date.getTime());
        d.setYear(d.getYear() + intervalYear);
        d.setMonth(d.getMonth() + intervalMonth);
        d.setDate(d.getDate() + intervalDay);
        return d;
    }

    public static Date parseDbDate(String sdate) throws ParseException {
        String pattern = "yyyyMMddHHmmss";

        int length = sdate.length();

        SimpleDateFormat sdf = new SimpleDateFormat(pattern.substring(0, length));

        Date d = sdf.parse(sdate);
        return d;
    }

    public static String formatDbDate(Date date, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.format(date);
    }

}
