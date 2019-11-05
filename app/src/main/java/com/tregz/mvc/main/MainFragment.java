package com.tregz.mvc.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.tregz.mvc.R;
import com.tregz.mvc.base.BaseFragment;
import com.tregz.mvc.core.date.DateUtil;
import com.tregz.mvc.core.read.ReadUtil;
import com.tregz.mvc.data.DataModel;
import com.tregz.mvc.data.item.ItemApple;
import com.tregz.mvc.main.list.ListCollection;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY;

public class MainFragment extends BaseFragment {

    private TextView log;
    private TextView sum;
    private EditText editor;
    private String input;
    private MainListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (MainListener) context;
        } catch (ClassCastException e) {
            String name = MainListener.class.getSimpleName();
            throw new ClassCastException(context.toString() + " must implement " + name);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        listener.onFragmentStart("Main");
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        Log.d(TAG, State.VIEW_INFLATING.name());
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListCollection.clear();

        log = view.findViewById(R.id.log);
        log.setMovementMethod(new ScrollingMovementMethod());
        sum = view.findViewById(R.id.sum);
        ItemApple apple1 = new ItemApple(new Date());  // today
        onAppleCreated(ListCollection.add(apple1));
        apple1.setColor(R.color.colorAccent);
        ItemApple apple2 = new ItemApple(DateUtil.addMonth(new Date(), -1)); // last month
        onAppleCreated(ListCollection.add(apple2));
        apple2.setColor(android.R.color.white);
        ItemApple apple3 = new ItemApple(null);
        onAppleCreated(ListCollection.add(apple3));
        apple3.setColor(R.color.colorPrimary);
        apple();

        editor = view.findViewById(R.id.input_editor);
        editor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String sequence = charSequence.toString();
                if (!sequence.isEmpty()) input = sequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing
            }
        });

        view.findViewById(R.id.negative_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.setText("");
                sum.setText("");
            }
        });

        view.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer number = ReadUtil.parse(input);
                if (number != null && number == ListCollection.getListCount())
                    sum.setText(getString(R.string.answer_positive));
                else sum.setText(getString(R.string.answer_negative));
            }
        });
    }

    private void apple() {
        ItemApple apple = new ItemApple(new Date());
        apple.setColor(R.color.colorPrimary);
        onAppleCreated(ListCollection.add(apple));
    }

    private void onAppleCreated(@NonNull DataModel item) {
        if (item instanceof ItemApple) {
            log.append(HtmlCompat.fromHtml("<b>Pomme ajoutée</b>", FROM_HTML_MODE_LEGACY));
            String skeleton = "d MMMM yyyy";
            SimpleDateFormat formatter = new SimpleDateFormat(skeleton, Locale.getDefault());
            Date ripe = ((ItemApple)item).getRipe();
            String day = ripe != null ? formatter.format(ripe) : null;
            String unknown = "Non cueillie ou date de cueillette inconnue.";
            String riped = day != null ? "Cueillie le " + day + "." : unknown;
            log.append("\n" + riped + "\n");
            String total = "Taille de la liste: " + ListCollection.getListCount();
            sum.setText(total);
        }
    }

    static {
        TAG = MainFragment.class.getSimpleName();
    }
}
