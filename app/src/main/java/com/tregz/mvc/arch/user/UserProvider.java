package com.tregz.mvc.arch.user;

import android.content.Context;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserProvider extends ContentProvider {

    public static final String AUTHORITY = "com.tregz.user_provider";
    private UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static int MODE = Context.MODE_PRIVATE;
    final int PREFERENCES = 1;

    @Override
    public boolean onCreate() {
        matcher.addURI(AUTHORITY, "*/*", PREFERENCES);
        return true; // Always return true, indicating that the provider loaded correctly.
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0; // Always returns "no rows affected" (0)
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null; // Return no type for MIME type
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null; // Always returns null (no URI)
    }

    @Nullable
    @Override
    public Cursor query(
            @NonNull Uri uri,
            @Nullable String[] strings,
            @Nullable String s,
            @Nullable String[] strings1,
            @Nullable String s1
    ) {
        if (matcher.match(uri) == PREFERENCES) {
            String key = uri.getPathSegments().get(0);
            MatrixCursor cursor = new MatrixCursor(new String[] { key });
            Context context = getContext();
            if (context != null) {
                SharedPreferences sp = context.getSharedPreferences(UserResolver.TAG, MODE);
                if (!sp.contains(key)) return cursor;
                cursor.newRow().add(getValue(sp, uri, key, uri.getPathSegments().get(1)));
            }
            return cursor;
        } else throw new IllegalArgumentException("Unsupported uri: " + uri);
    }

    private Object getValue(SharedPreferences sp, Uri uri, String key, String type) {
        switch(type) {
            case "string": return sp.getString(key, null);
            case "boolean": return sp.getBoolean(key, false) ? 1 : 0;
            default: throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }

    @Override
    public int update(
            @NonNull Uri uri,
            @Nullable ContentValues contentValues,
            @Nullable String s,
            @Nullable String[] strings
    ) {
        return 0; // Always returns "no rows affected" (0)
    }
}
