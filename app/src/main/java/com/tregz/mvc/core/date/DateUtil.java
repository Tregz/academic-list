package com.tregz.mvc.core.date;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {

    public static Date addMonth(Date then, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(then);
        calendar.add(Calendar.MONTH, i);
        return calendar.getTime();
    }

    public static Date parse(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(date);
        } catch (ParseException e) {
            if (e.getMessage() != null) { Log.e(DateUtil.class.getSimpleName(), e.getMessage()); }
            return null;
        }
    }

}
