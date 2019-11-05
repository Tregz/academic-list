package com.tregz.mvc.main.user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tregz.mvc.R;
import com.tregz.mvc.arch.user.UserShared;
import com.tregz.mvc.base.BaseActivity;
import com.tregz.mvc.base.BaseFragment;
import com.tregz.mvc.core.date.DateUtil;
import com.tregz.mvc.data.DataType;
import com.tregz.mvc.data.user.UserModel;
import com.tregz.mvc.main.MainFragment;
import com.tregz.mvc.main.list.ListCollection;

public class UserFragment extends BaseFragment {

    private UserModel user;
    private UserShared preferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContext() != null && preferences == null) preferences = new UserShared(getContext());

        // Is fragment restored?
        Log.d(TAG, "SavedInstance: " + (savedInstanceState != null));
        if (savedInstanceState != null) {
            user = savedInstanceState.getParcelable(UserModel.TAG);
            if (user != null && user.getEmail() != null) Log.d(TAG, user.getEmail());
        } else user = new UserModel();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        Log.d(TAG, MainFragment.State.VIEW_INFLATING.name());
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText emailEditor = view.findViewById(R.id.email_editor);
        emailEditor.setOnFocusChangeListener(emailListener);
        if (preferences != null) emailEditor.setText(preferences.getEmail());
        final EditText birthDate = view.findViewById(R.id.birth_date_editor);
        birthDate.setOnFocusChangeListener(birthDateListener);
        final EditText firstName = view.findViewById(R.id.first_name_editor);
        firstName.setOnFocusChangeListener(firstNameListener);
        final EditText lastName = view.findViewById(R.id.last_name_editor);
        lastName.setOnFocusChangeListener(lastNameListener);
        final EditText phoneNumber = view.findViewById(R.id.phone_number_editor);
        phoneNumber.setOnFocusChangeListener(phoneNumberListener);
        final CheckBox checkBox = view.findViewById(R.id.allergic_box);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                user.setAllergic(isChecked);
            }
        });
        checkBox.setChecked(user.isAllergic());
        Spinner spinner = view.findViewById(R.id.genre_spinner);
        if (getContext() != null) {
            int array = R.array.array_genre;
            int label = R.layout.spinner_label;
            spinner.setAdapter(ArrayAdapter.createFromResource(getContext(), array, label));
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user.setGender(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        spinner.setSelection(user.getGender());

        view.findViewById(R.id.negative_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthDate.setText("");
                emailEditor.setText("");
                firstName.setText("");
                lastName.setText("");
                phoneNumber.setText("");
                checkBox.setSelected(false);
            }
        });

        view.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, user.getEmail());
                ListCollection.add(user);
                UserFragmentDirections.ActionAuthFragmentToListFragment action;
                action = UserFragmentDirections.actionAuthFragmentToListFragment();
                action.setType(DataType.USER.ordinal());
                if (getActivity() != null) ((BaseActivity)getActivity()).navigate(action);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        listener.onFragmentStart("Profile");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(UserModel.TAG, user);
    }

    private View.OnFocusChangeListener birthDateListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            String sequence = ((EditText)view).getText().toString();
            if (!sequence.isEmpty()) {
                if (UserUtil.isDateValid(sequence)) user.setBirthDate(DateUtil.parse(sequence));
                else ((EditText)view).setError("Birth date is not valid");
            }
        }
    };

    private View.OnFocusChangeListener emailListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            String sequence = ((EditText)view).getText().toString();
            if (!sequence.isEmpty()) {
                if (UserUtil.isEmailValid(sequence)) {
                    user.setEmail(sequence);
                    if (preferences != null) preferences.setEmail(sequence);
                }
                else ((EditText)view).setError("Email is not valid");
            }
        }
    };

    private View.OnFocusChangeListener firstNameListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            String sequence = ((EditText)view).getText().toString();
            if (!sequence.isEmpty()) {
                if (UserUtil.isPersonNameValid(sequence)) user.setFirstName(sequence);
                else ((EditText)view).setError("First name is not valid");
            }
        }
    };

    private View.OnFocusChangeListener lastNameListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            String sequence = ((EditText)view).getText().toString();
            if (!sequence.isEmpty()) {
                if (UserUtil.isPersonNameValid(sequence)) user.setLastName(sequence);
                else ((EditText)view).setError("Last name is not valid");
            }
        }
    };

    private View.OnFocusChangeListener phoneNumberListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            String sequence = ((EditText)view).getText().toString();
            if (!sequence.isEmpty()) {
                if (UserUtil.isPhoneValid(sequence)) user.setPhoneNumber(Long.parseLong(sequence));
                else ((EditText)view).setError("Phone number is not valid");
            }
        }
    };

    static {
        TAG = UserFragment.class.getSimpleName();
    }
}
