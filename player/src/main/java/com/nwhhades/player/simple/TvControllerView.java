package com.nwhhades.player.simple;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.hjq.toast.Toaster;
import com.nwhhades.common.view.TvSeekBar;
import com.nwhhades.player.R;
import com.nwhhades.player.databinding.LayoutTvControllerViewBinding;
import com.nwhhades.player.impl.BaseControllerView;

public class TvControllerView extends BaseControllerView<LayoutTvControllerViewBinding> implements View.OnFocusChangeListener, View.OnClickListener, View.OnKeyListener {

    @Override
    protected LayoutTvControllerViewBinding createBinding(LayoutInflater layoutInflater) {
        return LayoutTvControllerViewBinding.inflate(layoutInflater);
    }

    @Override
    public void addListener() {
        if (binding != null) {
            binding.tpProgress.setOnTvSeekBarListener(new TvSeekBar.OnTvSeekBarListener() {
                @Override
                public void onProgressChanged(TvSeekBar tvSeekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartPreview(TvSeekBar tvSeekBar, int progress) {
                    cancelAutoHide();
                }

                @Override
                public void onStopPreview(TvSeekBar tvSeekBar, int progress) {
                    autoHide();
                    if (mOnControllerActionListener != null) {
                        mOnControllerActionListener.onUserChangeProgress(progress);
                    }
                }

                @Override
                public void onBack() {
                    hide();
                }
            });

            binding.btn1.setOnFocusChangeListener(this);
            binding.btn2.setOnFocusChangeListener(this);
            binding.btn3.setOnFocusChangeListener(this);
            binding.btn4.setOnFocusChangeListener(this);
            binding.btn1.setOnClickListener(this);
            binding.btn2.setOnClickListener(this);
            binding.btn3.setOnClickListener(this);
            binding.btn4.setOnClickListener(this);
            binding.btn1.setOnKeyListener(this);
            binding.btn2.setOnKeyListener(this);
            binding.btn3.setOnKeyListener(this);
            binding.btn4.setOnKeyListener(this);
        }
    }

    @Override
    public void delListener() {
        if (binding != null) {
            binding.tpProgress.setOnTvSeekBarListener(null);
            binding.btn1.setOnFocusChangeListener(null);
            binding.btn2.setOnFocusChangeListener(null);
            binding.btn3.setOnFocusChangeListener(null);
            binding.btn4.setOnFocusChangeListener(null);
            binding.btn1.setOnClickListener(null);
            binding.btn2.setOnClickListener(null);
            binding.btn3.setOnClickListener(null);
            binding.btn4.setOnClickListener(null);
            binding.btn1.setOnKeyListener(null);
            binding.btn2.setOnKeyListener(null);
            binding.btn3.setOnKeyListener(null);
            binding.btn4.setOnKeyListener(null);
        }
    }

    @Override
    public void setTitle(String title) {
        if (binding != null) {
            binding.tvTitle.setText(title);
        }
    }

    @Override
    public void setProgress(int progress) {
        if (binding != null) {
            binding.tpProgress.setProgress(progress);
        }
    }

    @Override
    public void setProgress(long time1, long time2) {
        if (binding != null) {
            binding.tpProgress.setProgress(time1, time2);
        }
    }

    @Override
    public void setPlayingUI() {
        binding.btn1.setBackgroundResource(R.drawable.ic_pause);
    }

    @Override
    public void setPauseUI() {
        binding.btn1.setBackgroundResource(R.drawable.ic_play);
    }

    @Override
    public void setErrUI() {
        Toaster.show("播放出错");
        binding.btn1.setBackgroundResource(R.drawable.ic_play);
    }

    @Override
    public void setCompletedUI() {
        Toaster.show("播放结束");
        binding.btn1.setBackgroundResource(R.drawable.ic_play);
        binding.tpProgress.setProgress(0);
    }

    @Override
    public void setMuteUI(boolean mute) {
        if (mute) {
            binding.btn3.setBackgroundResource(R.drawable.ic_voice);
        } else {
            binding.btn3.setBackgroundResource(R.drawable.ic_mute);
        }
    }

    @Override
    public void showRequestFocus() {
        binding.btn1.requestFocus();
    }

    @Override
    public void onClick(View v) {
        if (mOnControllerActionListener != null) {
            mOnControllerActionListener.onClickView(v);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            show();
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            hide();
            return true;
        }
        return false;
    }

}
