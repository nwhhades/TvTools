package com.nwhhades.common.net;

import android.util.Log;

import com.blankj.utilcode.util.GsonUtils;
import com.hjq.http.request.HttpRequest;
import com.tencent.mmkv.MMKV;

public class HttpCacheManager {

    private static final class InstanceHolder {
        private static final HttpCacheManager instance = new HttpCacheManager();
    }

    public static HttpCacheManager getInstance() {
        return InstanceHolder.instance;
    }

    private static final String TAG = "HttpCacheManager";

    private final MMKV kv;

    private HttpCacheManager() {
        kv = MMKV.mmkvWithID(TAG);
    }

    public boolean put(HttpRequest<?> httpRequest, String data) {
        if (httpRequest == null) {
            return false;
        }
        try {
            Cache cache = new Cache(System.currentTimeMillis(), data);
            data = GsonUtils.toJson(cache);
            return kv.putString(getKey(httpRequest), data).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String get(HttpRequest<?> httpRequest, long cache_time) {
        if (httpRequest == null) {
            return null;
        }
        try {
            String data = kv.getString(getKey(httpRequest), "");
            Cache cache = GsonUtils.fromJson(data, Cache.class);
            if (cache != null) {
                long now = System.currentTimeMillis();
                long time = cache.getTime() + cache_time;
                if (time >= now) {
                    return cache.getData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clear() {
        kv.clearMemoryCache();
        kv.clear();
    }

    /**
     * 生成保存的key
     *
     * @param httpRequest 请求
     * @return key
     */
    private String getKey(HttpRequest<?> httpRequest) {
        if (httpRequest == null) {
            return "";
        }
        String key = httpRequest.getRequestHost().getHost() + httpRequest.getRequestApi().getApi();
        Log.d(TAG, "getKey: " + key);
        return key;
    }

    private final static class Cache {

        private long time;
        private String data;

        public Cache(long time, String data) {
            this.time = time;
            this.data = data;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

    }

}
