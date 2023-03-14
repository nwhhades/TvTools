package com.nwhhades.common.base;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.hjq.http.EasyConfig;
import com.hjq.toast.Toaster;
import com.nwhhades.common.net.RequestHandler;
import com.nwhhades.common.net.RequestServer;
import com.nwhhades.common.toaster.BigBlackToastStyle;
import com.tencent.mmkv.MMKV;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public abstract class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initToaster();
        initPermissions();
        initUrlUtils();
        initHttp();
        init();
    }

    private void initHttp() {
        String root = MMKV.initialize(this);
        Log.d(TAG, "initHttp: http cache root is " + root);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
        EasyConfig.with(okHttpClient)
                // 是否打印日志
                .setLogEnabled(true)
                // 设置服务器配置（必须设置）
                .setServer(new RequestServer())
                // 设置请求处理策略（必须设置）
                .setHandler(new RequestHandler())
                // 设置请求重试次数
                .setRetryCount(3)
                // 设置请求重试间隔
                .setRetryTime(3000)
                // 启用配置
                .into();
    }

    protected void initToaster() {
        Toaster.init(this, new BigBlackToastStyle());
    }

    protected abstract void init();

    protected abstract void initUrlUtils();

    protected abstract void initPermissions();

}
