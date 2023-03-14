package com.nwhhades.common.net;

import androidx.annotation.NonNull;

import com.hjq.http.EasyHttp;
import com.hjq.http.annotation.HttpIgnore;
import com.hjq.http.config.IRequestApi;
import com.hjq.http.config.IRequestHost;
import com.hjq.http.lifecycle.ApplicationLifecycle;
import com.hjq.http.model.ResponseClass;
import com.hjq.http.request.GetRequest;

public class StringApi implements IRequestApi, IRequestHost {

    @HttpIgnore
    private final String url;

    public StringApi(String url) {
        this.url = url;
    }

    @NonNull
    @Override
    public String getApi() {
        return "";
    }

    @NonNull
    @Override
    public String getHost() {
        return url;
    }

    public String get() {
        String data = null;
        GetRequest getRequest = EasyHttp.get(ApplicationLifecycle.getInstance());
        try {
            data = getRequest.api(this).execute(new ResponseClass<String>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
