package com.nwhhades.common.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.nwhhades.common.R;
import com.nwhhades.common.databinding.LayoutTvProgressBarBinding;

import java.util.Locale;

public class TvProgressBar extends ConstraintLayout implements TvSeekBar.OnTvSeekBarListener, View.OnFocusChangeListener {

    private volatile long total_time;
    private LayoutTvProgressBarBinding binding;
    private TvSeekBar.OnTvSeekBarListener onTvSeekBarListener;

    public TvProgressBar(@NonNull Context context) {
        super(context);
        init();
    }

    public TvProgressBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TvProgressBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutTvProgressBarBinding.inflate(LayoutInflater.from(getContext()), this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS);
        setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.tsbProgress.requestFocus();
            }
        });
        binding.vTop.setOnFocusChangeListener(this);
        binding.vBottom.setOnFocusChangeListener(this);
        binding.vLeft.setOnFocusChangeListener(this);
        binding.vRight.setOnFocusChangeListener(this);
        binding.tsbProgress.setOnTvSeekBarListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (isStartPre) {
                onStopPreview(binding.tsbProgress, binding.tsbProgress.getProgress());
            }
            actionFocus(v.getId());
        }
    }

    private boolean isStartPre = false;

    @Override
    public void onProgressChanged(TvSeekBar tvSeekBar, int progress, boolean fromUser) {
        if (total_time > 0 && fromUser) {
            float local_cur_time = progress / 100f * total_time;
            setTime1((long) local_cur_time);
        }
        if (onTvSeekBarListener != null) {
            onTvSeekBarListener.onProgressChanged(binding.tsbProgress, progress, fromUser);
        }
    }

    @Override
    public void onStartPreview(TvSeekBar tvSeekBar, int progress) {
        isStartPre = true;
        if (onTvSeekBarListener != null) {
            onTvSeekBarListener.onStartPreview(binding.tsbProgress, progress);
        }
    }

    @Override
    public void onStopPreview(TvSeekBar tvSeekBar, int progress) {
        if (!isStartPre) {
            return;
        }
        isStartPre = false;
        if (onTvSeekBarListener != null) {
            onTvSeekBarListener.onStopPreview(binding.tsbProgress, progress);
        }
    }

    @Override
    public void onBack() {
        if (onTvSeekBarListener != null) {
            onTvSeekBarListener.onBack();
        }
    }

    private void actionFocus(int view_id) {
        int next_view_id = 0;
        if (view_id == R.id.v_top) {
            next_view_id = getNextFocusUpId();
        } else if (view_id == R.id.v_bottom) {
            next_view_id = getNextFocusDownId();
        } else if (view_id == R.id.v_left) {
            next_view_id = getNextFocusLeftId();
        } else if (view_id == R.id.v_right) {
            next_view_id = getNextFocusRightId();
        }
        //查找view
        if (getContext() instanceof Activity && next_view_id != 0) {
            Activity activity = (Activity) getContext();
            View view = activity.findViewById(next_view_id);
            if (view != null) {
                view.requestFocus();
            }
        }
    }

    private void setTime1(long time1) {
        binding.tvTime1.setText(formatSeconds(time1));
    }

    private void setTime2(long time2) {
        total_time = time2;
        binding.tvTime2.setText(formatSeconds(time2));
    }

    public void setOnTvSeekBarListener(TvSeekBar.OnTvSeekBarListener onTvSeekBarListener) {
        this.onTvSeekBarListener = onTvSeekBarListener;
    }

    public void setProgress(int progress) {
        binding.tsbProgress.setProgress(progress);
    }

    public void setProgress(long time1, long time2) {
        if (time1 < 0 || time2 <= 0) {
            return;
        }
        if (!isStartPre) {
            float progress = time1 * 100f / time2;
            binding.tsbProgress.setProgress((int) progress);
            setTime1(time1);
            setTime2(time2);
        }
    }

    public static String formatSeconds(long timeMs) {
        long seconds = timeMs / 1000;
        String standardTime;
        if (seconds <= 0) {
            standardTime = "00:00";
        } else if (seconds < 60) {
            standardTime = String.format(Locale.getDefault(), "00:%02d", seconds % 60);
        } else if (seconds < 3600) {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
        } else {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", seconds / 3600, seconds % 3600 / 60, seconds % 60);
        }
        return standardTime;
    }

}
