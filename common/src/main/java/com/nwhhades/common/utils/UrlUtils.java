package com.nwhhades.common.utils;

public enum UrlUtils {
    INSTANCE;

    private Impl impl;

    public void setImpl(Impl impl) {
        this.impl = impl;
    }

    //使用
    public String useUrl(String url, String tag) {
        if (impl != null) {
            return impl.actionUrl(url, tag);
        }
        return url;
    }

    //具体的操作 留给后面来实现
    public interface Impl {
        String actionUrl(String url, String tag);
    }

}
