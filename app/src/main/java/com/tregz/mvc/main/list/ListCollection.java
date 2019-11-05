package com.tregz.mvc.main.list;

import android.util.SparseArray;

import androidx.annotation.NonNull;

import com.tregz.mvc.data.DataModel;

import java.util.ArrayList;
import java.util.List;

public class ListCollection {

    private static final List<DataModel> list = new ArrayList<>();
    private static final SparseArray<DataModel> map = new SparseArray<>();

    public static int getListCount() {
        return list.size();
    }

    public static void clear() {
        list.clear();
        map.clear();
    }

    static List<DataModel> getList() {
        return list;
    }

    public static DataModel add(@NonNull DataModel apple) {
        list.add(apple);
        map.put(map.size(), apple);
        return apple;
    }

}
