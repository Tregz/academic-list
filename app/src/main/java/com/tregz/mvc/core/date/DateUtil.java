package com.tregz.mvc.core.date;

import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

    public static Date addMonth(Date then, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(then);
        calendar.add(Calendar.MONTH, i);
        return calendar.getTime();
    }

}
