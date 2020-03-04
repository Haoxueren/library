package com.haoxueren.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.haoxueren.library.R;
import com.haoxueren.library.log.MyLog;

import java.util.Locale;

/**
 * 支持通过自定义属性配置最多输入的整数和小数位数
 * <p>
 * maxInteger：最多输入的整数位数
 * maxDecimal：最多输入的小数位数
 * <p>
 * create by haomingliang on 2020/3/4
 */
public class DigitEditText extends AppCompatEditText implements TextWatcher {

    private int position = 0; // 超出当前限定的位数，删除新输入的数字

    private AttributeHolder holder;

    public DigitEditText(Context context) {
        this(context, null);
    }

    public DigitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = new AttributeHolder(context, attrs);
        this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        this.addTextChangedListener(this);
    }

    public void setMaxInteger(int maxInteger) {
        holder.setMaxInteger(maxInteger);
    }

    public void setMaxDecimal(int maxDecimal) {
        holder.setMaxDecimal(maxDecimal);
    }

    /**
     * 限制EditText输入，最多6位整数，4位小数
     */
    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        // 小数点前自动补0
        if (text.matches("[.]\\d*")) {
            editable.insert(0, "0");
            return;
        }
        // 删除数字前的0(逐位删除)
        if (text.matches("0[0-9][0-9.]*")) {
            editable.delete(0, 1);
            return;
        }
        // 最多输入m位整数(逐位删除)
        if (text.matches(holder.integerRegex)) {
            editable.delete(0, 1);
            return;
        }
        // 最多输入n位小数(逐位删除)
        if (text.matches(holder.decimalRegex)) {
            int last = length();
            editable.delete(last - 1, last);
            return;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        this.position = start;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    class AttributeHolder {

        private String integerRegex;
        private String decimalRegex;

        AttributeHolder(Context context, AttributeSet attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DigitEditText);
            // 整数最大位数，默认为10
            int maxInteger = typedArray.getInt(R.styleable.DigitEditText_maxInteger, 10);
            // 小数最大位数，默认为10
            int maxDecimal = typedArray.getInt(R.styleable.DigitEditText_maxDecimal, 10);
            integerRegex = String.format(Locale.CHINA, "\\d{%d,}[0-9.]*", maxInteger + 1);
            decimalRegex = String.format(Locale.CHINA, "\\d+[.]\\d{%d,}", maxDecimal + 1);
            typedArray.recycle();
        }

        private void setMaxInteger(int maxInteger) {
            integerRegex = String.format(Locale.CHINA, "\\d{%d,}[0-9.]*", maxInteger + 1);
        }

        private void setMaxDecimal(int maxDecimal) {
            decimalRegex = String.format(Locale.CHINA, "\\d+[.]\\d{%d,}", maxDecimal + 1);
        }
    }


}
