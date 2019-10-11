package com.tregz.mvc.core.read;

import android.util.Log;

public final class ReadUtil {
    private final static String TAG = ReadUtil.class.getSimpleName();

    public static Integer parse(String input) {
        try {
            return (Integer.parseInt(input));
        } catch (NumberFormatException e) {
            if (e.getMessage() != null) Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
