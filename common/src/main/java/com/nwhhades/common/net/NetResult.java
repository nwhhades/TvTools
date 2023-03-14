package com.nwhhades.common.net;

import androidx.annotation.NonNull;

public class NetResult<T> {

    private int code;
    private String msg;
    private T data;
    private int page;
    private int total;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @NonNull
    @Override
    public String toString() {
        return "NetResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", page=" + page +
                ", total=" + total +
                '}';
    }

}