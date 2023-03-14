package com.nwhhades.player.ijk;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import xyz.doikki.videoplayer.player.VideoView;

public class IjkView extends VideoView {

    public IjkView(@NonNull Context context) {
        super(context);
        init();
    }

    public IjkView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IjkView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setPlayerFactory(MyIjkPlayerFactory.create());
        mCurrentScreenScaleType = SCREEN_SCALE_MATCH_PARENT;
    }

    @Override
    protected void setOptions() {
        super.setOptions();
    }

}
