package com.tregz.mvc.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tregz.mvc.R;
import com.tregz.mvc.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainListener {

    private MenuItem icPerson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                icPerson = item;
                if (!back()) {
                    int action = R.id.action_mainFragment_to_authFragment;
                    MainNavigation.getInstance().navigate(this, action);
                    item.setIcon(R.drawable.ic_arrow_back);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
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
        if (MainNavigation.getInstance().fragmentId(this) != R.id.mainFragment) {
            MainNavigation.getInstance().pop(this);
            if (icPerson != null) icPerson.setIcon(R.drawable.ic_person);
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
