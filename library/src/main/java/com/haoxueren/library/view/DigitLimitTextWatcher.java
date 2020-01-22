package com.haoxueren.library.view;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * 限制EditText输入，最多6位整数，4位小数
 */
public class DigitLimitTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        // 小数点前自动补0
        if (text.matches("[.]\\d+")) {
            editable.insert(0, "0");
            return;
        }
        // 删除数字前的0(逐位删除)
        if (text.matches("0[0-9][0-9.]+")) {
            editable.delete(0, 1);
            return;
        }
        int length = text.length();
        // 最多输入6位整数(逐位删除)
        if (text.matches("\\d{7,}[0-9.]*")) {
            editable.delete(length - 1, length);
            return;
        }
        // 最多输入4位小数(逐位删除)
        if (text.matches("\\d{0,6}[.]\\d{5,}")) {
            editable.delete(length - 1, length);
            return;
        }
    }
}