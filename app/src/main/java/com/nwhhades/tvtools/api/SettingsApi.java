package com.nwhhades.tvtools.api;

import androidx.annotation.NonNull;

import com.hjq.http.model.CacheMode;
import com.nwhhades.common.net.BaseApi;

public class SettingsApi extends BaseApi<SettingsBean> {

    @NonNull
    @Override
    public String getApi() {
        return "api/settings/getSettings";
    }

    @NonNull
    @Override
    public CacheMode getCacheMode() {
        return CacheMode.USE_CACHE_AFTER_FAILURE;
    }

    @Override
    public long getCacheTime() {
        return 1000 * 60 * 60;
    }

}
