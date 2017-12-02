package com.android.rdc.librarysystem.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String DAY_FORMAT = "yyyy-MM-dd";
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat sMinuteFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat sHourTimeFormat = new SimpleDateFormat("HH:mm");
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat sDayFormat = new SimpleDateFormat(DAY_FORMAT);


    private DateUtil() {
    }

    public static String minuteDateFormat(Date date) {
        return date == null ? "" : sMinuteFormat.format(date);
    }

    public static String hourTimeFormat(Date date) {
        if (date == null) {
            return "";
        }
        return sHourTimeFormat.format(date);
    }

    public static String dayFormat(Date date) {
        if (date == null) {
            return "";
        }
        return sDayFormat.format(date);
    }

}
