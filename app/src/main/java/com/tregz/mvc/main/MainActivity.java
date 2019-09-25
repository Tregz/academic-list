package com.tregz.mvc.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.tregz.mvc.R;
import com.tregz.mvc.data.DataApple;
import com.tregz.mvc.core.date.DateUtil;
import com.tregz.mvc.list.ListApple;
import com.tregz.mvc.view.ViewApple;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements ViewApple {

    private final String TAG = MainActivity.class.getSimpleName();
    private final ListApple listApple = new ListApple(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataApple apple1 = new DataApple(new Date());  // today
        listApple.add(apple1);
        listApple.add(apple1);
        apple1.setColor(R.color.colorAccent);
        Log.i(TAG, "La pomme 1 est comestible? " + apple1.edible());
        Log.i(TAG, "La pomme 1 est de couleur: " + apple1.getColor());

        DataApple apple2 = new DataApple(DateUtil.addMonth(new Date(), -1)); // last month
        listApple.add(apple2);
        apple2.setColor(android.R.color.white);
        Log.i(TAG, "La pomme 2 est comestible? " + apple2.edible());
        Log.i(TAG, "La pomme 2 est de couleur: " + apple2.getColor());

        DataApple apple3 = new DataApple(null);
        listApple.add(apple3);
        apple3.setColor(R.color.colorPrimary);
        Log.i(TAG, "La pomme 3 est comestible? " + apple3.edible());
        Log.i(TAG, "La pomme 3 est de couleur: " + apple3.getColor());

        DataApple apple4 = apple();
        Log.i(TAG, "La pomme 4 est comestible? " + apple4.edible());
        Log.i(TAG, "La pomme 4 est de couleur: " + apple4.getColor());
    }

    @Override
    public void onAppleCreated(int listSize, int setSize) {
        Log.i(TAG, "Pomme ajoutée");
        Log.i(TAG, "Taille de la liste: " + listSize);
        Log.i(TAG, "Taille de l'ensemble: " + setSize);
    }

    public DataApple apple() {
        DataApple apple = new DataApple(new Date());
        apple.setColor(R.color.colorPrimary);
        listApple.add(apple);
        return apple;
    }
}
