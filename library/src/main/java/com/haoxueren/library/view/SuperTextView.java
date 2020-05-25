package com.haoxueren.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;


import com.haoxueren.library.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 1. 提供默认的hint，内容为"--"，颜色与textColor保持一致
 * 2. 提供stringFormat属性，根据用户配置的模板格式化
 * 3. 提供numberFormat属性，根据用户配置的模板格式化
 * <p>
 * create by haomingliang on 2020/2/27
 */
public class SuperTextView extends AppCompatTextView implements TextWatcher {

    private AttributeHolder holder;
    private CharSequence textHint = "--";

    public SuperTextView(Context context) {
        this(context, null);
    }

    public SuperTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        holder = new AttributeHolder(context, attrs);
        textHint = TextUtils.isEmpty(getHint()) ? textHint : getHint();
        int textColor = this.getTextColors().getDefaultColor();
        this.setHintTextColor(textColor);
        this.setHint(textHint);
        this.addTextChangedListener(this);
    }

    /**
     * 根据用户设置的模式自动格式化文本
     * 如果两种模式都设置了，则先应用StringFormat再应用NumberFormat
     */
    public void setSuperText(String text) {
        boolean stringPatternEmpty = TextUtils.isEmpty(holder.stringPattern);
        boolean numberPatternEmpty = TextUtils.isEmpty(holder.numberPattern);
        if (stringPatternEmpty) {
            if (numberPatternEmpty) {
                // 1.两者均为空
                this.setText(text);
            } else {
                // 2.只有NumberFormat
                this.setNumberFormatText(text);
            }
        } else {
            if (numberPatternEmpty) {
                // 3.只有StringFormat
                this.setStringFormatText(text);
            } else {
                // 4.两者均不为空
                this.setNumberFormatText(text);
                String newText = this.getText().toString();
                this.setStringFormatText(newText);
            }
        }
    }

    /**
     * 使用NumberFormat格式化数据
     */
    public void setNumberFormatText(@NonNull String text) {
        // 文本为空
        if (text.length() == 0) {
            this.setText(text);
            return;
        }
        // 模式为空
        if (TextUtils.isEmpty(holder.numberPattern)) {
            this.setText(text);
            return;
        }
        // 非数字直接显示
        if (!text.matches("[-+]?\\d*[.]?\\d*")) {
            this.setText(text);
            return;
        }
        // 使用NumberFormat格式化
        DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getInstance();
        numberFormat.applyPattern(holder.numberPattern);
        String formatText = numberFormat.format(new BigDecimal(text));
        this.setText(formatText);
    }

    /**
     * 使用StringFormat格式化数据
     */
    public void setStringFormatText(@NonNull String... texts) {
        // array is empty
        if (texts.length == 0) {
            this.setText("");
            return;
        }
        // pattern isn't null and array not empty
        String pattern = holder.stringPattern;
        if (!TextUtils.isEmpty(pattern)) {
            String formatText = String.format(pattern, texts);
            this.setText(formatText);
            return;
        }
        // pattern is null and array not empty
        StringBuilder builder = new StringBuilder();
        for (String text : texts) {
            builder.append(text);
        }
        this.setText(builder);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = s.toString();
        // 如果无数据，显示默认HINT
        if (TextUtils.isEmpty(text)) {
            this.setHint(textHint);
        }
    }

    class AttributeHolder {

        String numberPattern, stringPattern;

        AttributeHolder(Context context, AttributeSet attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperTextView);
            numberPattern = typedArray.getString(R.styleable.SuperTextView_numberFormat);
            stringPattern = typedArray.getString(R.styleable.SuperTextView_stringFormat);
            typedArray.recycle();
        }
    }
}
