package com.tregz.mvc.list;

import android.util.SparseArray;

import androidx.annotation.NonNull;

import com.tregz.mvc.data.DataApple;
import com.tregz.mvc.view.ViewApple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListApple {

    //private String TAG = ListApple.class.getSimpleName();

    private ViewApple view;

    public ListApple(ViewApple view) {
        this.view = view;
    }

    private final List<DataApple> list = new ArrayList<>();
    private final Set<DataApple> set = new HashSet<>();
    private final SparseArray<DataApple> map = new SparseArray<>();

    public void add(@NonNull DataApple apple) {
        list.add(apple);
        set.add(apple);
        map.put(map.size(), apple);
        view.onAppleCreated(list.size(), set.size());
    }

}
