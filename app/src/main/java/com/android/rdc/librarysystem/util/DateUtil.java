package com.android.rdc.librarysystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String DAY_FORMATE = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sMinuteFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sHourTimeFormat = new SimpleDateFormat("HH:mm");

    private DateUtil() {
    }

    public static String minuteDateFormat(Date date) {
        return date == null ? "" : sMinuteFormat.format(date);
    }

    public static String hourTimeFormat(Date date) {
        return sHourTimeFormat.format(date);
    }

    public static String dayFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

}
