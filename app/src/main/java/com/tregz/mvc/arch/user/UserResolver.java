package com.tregz.mvc.arch.user;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

class UserResolver {
    final static String TAG = UserResolver.class.getSimpleName();

    private String authority;
    private ContentResolver resolver;

    UserResolver(Context context, String authority) {
        this.authority = authority;
        resolver = context.getContentResolver();
    }

    private Uri getContentUri(String key, String type) {
        String content = "content://" + authority;
        return Uri.parse(content).buildUpon().appendPath(key).appendPath(type).build();
    }

    private Boolean getBooleanValue(Cursor cursor, Boolean defaultValue) {
        if (cursor == null) return defaultValue;
        Boolean value = defaultValue;
        if (cursor.moveToFirst()) value = cursor.getInt(0) > 0;
        cursor.close();
        return value;
    }

    private String getStringValue(Cursor cursor, String defaultValue) {
        if (cursor == null) return defaultValue;
        String value = defaultValue;
        if (cursor.moveToFirst()) value = cursor.getString(0);
        cursor.close();
        return value;
    }

    String getString(String key, String defaultValue) {
        return getStringValue(query(getContentUri(key, "string")), defaultValue);
    }

    Boolean getBoolean(String key, boolean defaultValue) {
        return getBooleanValue(query(getContentUri(key, "boolean")), defaultValue);
    }

    private Cursor query(Uri uri) {
        return resolver.query(uri, null, null, null, null);
    }
}
