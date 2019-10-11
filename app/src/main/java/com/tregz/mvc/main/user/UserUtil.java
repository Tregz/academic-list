package com.tregz.mvc.main.user;

import androidx.annotation.Nullable;

import java.util.regex.Pattern;

final class UserUtil {

    static boolean isEmailValid(@Nullable String input) {
        return input != null && input.matches("\\w+(\\.\\w+)*@[a-z]+(\\.[a-z]+)+");
    }

    static boolean isPersonNameValid(@Nullable String input) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]*$");
        return input != null && !input.isEmpty() && pattern.matcher(input).matches();
    }

    static boolean isDateValid(@Nullable String input) {
        String skeleton = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
        Pattern pattern = Pattern.compile(skeleton);
        return input != null && !input.isEmpty() && pattern.matcher(input).matches();
    }

    static boolean isPhoneValid(@Nullable String input) {
        Pattern pattern = Pattern.compile("/\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})/");
        return input != null && !input.isEmpty() && pattern.matcher(input).matches();
    }

}
