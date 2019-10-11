package com.tregz.mvc.arch.user;

import android.content.Context;
import android.content.SharedPreferences;

public class UserShared {

    private Context context;
    private final String EMAIL = "authentication_email";

    public UserShared(Context context) {
        this.context = context;
    }

    public String getEmail() {
        return getResolver().getString(EMAIL, "");
    }

    public void setEmail(String email) {
        editor().putString(EMAIL, email).apply();
    }

    private SharedPreferences.Editor editor() {
        return context.getSharedPreferences(UserResolver.TAG, UserProvider.MODE).edit();
    }

    private UserResolver getResolver() {
        return new UserResolver(context, UserProvider.AUTHORITY);
    }
}
