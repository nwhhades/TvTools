package com.nwhhades.common.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.GsonUtils;
import com.hjq.http.config.IRequestHandler;
import com.hjq.http.exception.DataException;
import com.hjq.http.exception.NullBodyException;
import com.hjq.http.exception.ResponseException;
import com.hjq.http.request.HttpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RequestHandler implements IRequestHandler {

    private static final String TAG = "RequestHandler";

    @NonNull
    @Override
    public Object requestSuccess(@NonNull HttpRequest<?> httpRequest, @NonNull Response response, @NonNull Type type) throws Exception {
        Log.d(TAG, "requestSuccess: " + type);
        //Response 直接返回
        if (Response.class.equals(type)) {
            return response;
        }
        //请求失败
        if (!response.isSuccessful()) {
            throw new ResponseException("请求异常", response);
        }
        //Headers
        if (Headers.class.equals(type)) {
            return response.headers();
        }
        //ResponseBody 为空
        ResponseBody body = response.body();
        if (body == null) {
            throw new NullBodyException("ResponseBody为空");
        }
        //ResponseBody
        if (ResponseBody.class.equals(type)) {
            return body;
        }
        //如果是用数组接收，判断一下是不是用 byte[] 类型进行接收的
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            if (byte.class.equals(genericComponentType)) {
                return body.bytes();
            }
        }
        //输入流
        if (InputStream.class.equals(type)) {
            return body.byteStream();
        }
        //图片
        if (Bitmap.class.equals(type)) {
            return BitmapFactory.decodeStream(body.byteStream());
        }
        String text;
        try {
            text = body.string();
            //如果是String直接返回
            if (String.class.equals(type)) {
                return text;
            }
        } catch (IOException e) {
            // 返回结果读取异常
            throw new DataException("读取数据异常", e);
        }
        return GsonUtils.fromJson(text, type);
    }

    @NonNull
    @Override
    public Exception requestFail(@NonNull HttpRequest<?> httpRequest, @NonNull Exception e) {
        e.printStackTrace();
        return e;
    }

    @Nullable
    @Override
    public Object readCache(@NonNull HttpRequest<?> httpRequest, @NonNull Type type, long cacheTime) {
        long now = System.currentTimeMillis();
        Log.d(TAG, "readCache: 当前时间：" + now + " - 缓存时效：" + cacheTime);
        String data = HttpCacheManager.getInstance().get(httpRequest, cacheTime);
        return GsonUtils.fromJson(data, type);
    }

    @Override
    public boolean writeCache(@NonNull HttpRequest<?> httpRequest, @NonNull Response response, @NonNull Object result) {
        return HttpCacheManager.getInstance().put(httpRequest, GsonUtils.toJson(result));
    }

    @Override
    public void clearCache() {
        HttpCacheManager.getInstance().clear();
    }

}
