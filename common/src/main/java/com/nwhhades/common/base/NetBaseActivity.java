package com.nwhhades.common.base;

import androidx.viewbinding.ViewBinding;

import com.hjq.http.listener.OnHttpListener;

import okhttp3.Call;

public abstract class NetBaseActivity<V extends ViewBinding> extends BaseActivity<V> implements OnHttpListener<Object> {

    @Override
    protected boolean enableLoadingView() {
        return true;
    }

    @Override
    public void onStart(Call call) {
        showLoadingView(null);
    }

    @Override
    public void onSucceed(Object o) {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onEnd(Call call) {
        hideLoadingView();
    }

}
