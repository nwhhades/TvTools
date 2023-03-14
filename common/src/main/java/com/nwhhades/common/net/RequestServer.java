package com.nwhhades.common.net;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestServer;

public class RequestServer implements IRequestServer {

    private static String BASE_URL = "http://www.baidu.com";

    @NonNull
    @Override
    public String getHost() {
        return BASE_URL;
    }

    public static void setBaseUrl(String url) {
        BASE_URL = url;
    }

}
