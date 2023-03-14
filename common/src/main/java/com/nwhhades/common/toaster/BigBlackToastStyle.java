package com.nwhhades.common.toaster;

import android.content.Context;
import android.util.TypedValue;

import com.hjq.toast.style.BlackToastStyle;

public class BigBlackToastStyle extends BlackToastStyle {

    //修改文本大小
    @Override
    protected float getTextSize(Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 25, context.getResources().getDisplayMetrics());
    }

}