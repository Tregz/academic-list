package com.tregz.mvc.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;

import com.tregz.mvc.R;
import com.tregz.mvc.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        back();
    }

    @Override
    public void onFragmentStart(@NonNull String title) {
        setTitle(title);
    }

    private boolean back() {
        if (fragmentId() != R.id.mainFragment) {
            pop();
            return true;
        } else return false;
    }

    /* private View root() {
        return getWindow().getDecorView().findViewById(android.R.id.content);
    } */

    static {
        TAG = MainActivity.class.getSimpleName();
    }
}
