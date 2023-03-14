package com.nwhhades.common.net;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import com.hjq.http.EasyHttp;
import com.hjq.http.config.IRequestApi;
import com.hjq.http.config.IRequestCache;
import com.hjq.http.listener.HttpCallback;
import com.hjq.http.listener.OnHttpListener;
import com.hjq.http.request.GetRequest;

public abstract class BaseApi<T> implements IRequestApi, IRequestCache {

    private static final String TAG = "BaseApi";

    public void get(@NonNull LifecycleOwner lifecycleOwner, @Nullable OnHttpListener<Object> listener, @Nullable String tag, long delay) {
        GetRequest request = EasyHttp.get(lifecycleOwner);
        request.api(this);
        if (tag != null) {
            request.tag(tag);
        }
        if (delay > 0) {
            request.delay(delay);
        }
        request.request(new HttpCallback<NetResult<T>>(listener) {

            @Override
            public void onSucceed(NetResult<T> result, boolean cache) {
                super.onSucceed(result, cache);
                Log.d(TAG, "onSucceed: " + result.toString());
                if (cache) {
                    Log.e(TAG, "onSucceed: 使用了缓存");
                }
            }

            @Override
            public void onFail(Exception e) {
                Log.e(TAG, "onFail: 请求失败", e);
                super.onFail(e);
            }

        });
    }

    public void cancel(@NonNull String tag) {
        EasyHttp.cancel(tag);
    }

    public void cancel(@NonNull LifecycleOwner lifecycleOwner) {
        EasyHttp.cancel(lifecycleOwner);
    }

}
