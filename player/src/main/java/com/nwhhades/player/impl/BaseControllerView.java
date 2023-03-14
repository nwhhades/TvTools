package com.nwhhades.player.impl;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewbinding.ViewBinding;

import com.nwhhades.player.base.IControllerView;

public abstract class BaseControllerView<V extends ViewBinding> implements IControllerView {

    protected boolean mShow = true;
    protected final long autoHideTime = 5000;
    protected final Handler handler = new Handler(Looper.myLooper());
    protected final Runnable hideRunnable = this::hide;

    protected OnControllerActionListener mOnControllerActionListener;
    protected V binding;

    protected abstract V createBinding(LayoutInflater layoutInflater);

    @Override
    public View getView(Context context) {
        if (binding == null) {
            binding = createBinding(LayoutInflater.from(context));
        }
        return binding.getRoot();
    }

    @Override
    public void addToVideoView(ViewGroup root) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = getView(root.getContext());
        int index = 1;
        if (view != null) {
            if (root.getChildCount() <= 1) {
                index = 0;
            }
            root.addView(view, index, layoutParams);
        }
    }

    @Override
    public void delToVideoView(ViewGroup root) {
        View view = getView(root.getContext());
        if (view != null) {
            root.removeView(view);
        }
    }

    @Override
    public void initView(ViewGroup root) {
        addToVideoView(root);
        addListener();
        hide();
    }

    @Override
    public void destroyView(ViewGroup root) {
        delListener();
        delToVideoView(root);
        binding = null;
    }

    @Override
    public void setOnControllerActionListener(OnControllerActionListener onControllerActionListener) {
        mOnControllerActionListener = onControllerActionListener;
    }

    @Override
    public void setTextToView(int view_id, String text) {
        if (binding != null) {
            View view = binding.getRoot().findViewById(view_id);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setText(text);
            }
        }
    }

    @Override
    public void setBackgroundResourceToView(int view_id, int res_id) {
        if (binding != null) {
            View view = binding.getRoot().findViewById(view_id);
            if (view != null) {
                view.setBackgroundResource(res_id);
            }
        }
    }

    @Override
    public void show() {
        autoHide();
        if (mShow) {
            return;
        }
        mShow = true;
        if (mOnControllerActionListener != null) {
            mOnControllerActionListener.onShow();
        }
        if (binding != null) {
            binding.getRoot().setVisibility(View.VISIBLE);
            showRequestFocus();
        }
    }

    @Override
    public void hide() {
        cancelAutoHide();
        if (!mShow) {
            return;
        }
        mShow = false;
        if (mOnControllerActionListener != null) {
            mOnControllerActionListener.onHide();
        }
        if (binding != null) {
            binding.getRoot().setVisibility(View.INVISIBLE);
            //强制焦点回到父
            if (binding.getRoot().getParent() instanceof View) {
                ((View) binding.getRoot().getParent()).requestFocus();
            }
        }
    }

    @Override
    public boolean isShow() {
        return mShow;
    }

    @Override
    public void toggleShow() {
        if (isShow()) {
            hide();
        } else {
            show();
        }
    }

    @Override
    public void autoHide() {
        cancelAutoHide();
        handler.postDelayed(hideRunnable, autoHideTime);
    }

    @Override
    public void cancelAutoHide() {
        handler.removeCallbacksAndMessages(null);
    }

}
