package com.tregz.mvc.main.list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tregz.mvc.R;
import com.tregz.mvc.base.BaseFragment;
import com.tregz.mvc.data.DataModel;
import com.tregz.mvc.data.DataType;
import com.tregz.mvc.data.user.UserModel;

public class ListFragment extends BaseFragment {

    private TextView log;
    private int type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            type = getArguments().getInt("type", 0);
        else type = 0;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        log = view.findViewById(R.id.log);
    }

    private void update() {
        log.setText("");
        Log.d(TAG, "List size " + ListCollection.getList().size());
        for (DataModel item : ListCollection.getList()) {
            if (type == DataType.USER.ordinal() && item instanceof UserModel) {
                String firstName = ((UserModel) item).getFirstName();
                String lastName = ((UserModel) item).getLastName();
                log.append(firstName + " " + lastName);
                // TODO more info
            }
        }
    }

    static {
        TAG = ListFragment.class.getSimpleName();
    }
}
