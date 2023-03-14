package com.nwhhades.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class TvButton extends AppCompatTextView implements View.OnFocusChangeListener {

    private static final String TAG = "TvButton";

    private boolean isScale = true;
    private OnFocusChangeListener onFocusChangeListener;

    public TvButton(@NonNull Context context) {
        super(context);
        init();
    }

    public TvButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TvButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.d(TAG, "init: 初始化按钮");
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (isScale) {
            if (hasFocus) {
                animate().scaleX(1.1f).scaleY(1.1f).start();
            } else {
                animate().scaleX(1.01f).scaleY(1.01f).setDuration(10).start();
            }
        }
        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    public void setScale(boolean scale) {
        isScale = scale;
    }

    public void setOnFocusListener(OnFocusChangeListener onFocusListener) {
        onFocusChangeListener = onFocusListener;
        setOnFocusChangeListener(this);
    }

}
