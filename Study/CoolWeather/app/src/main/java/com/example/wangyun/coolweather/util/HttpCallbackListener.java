package com.example.wangyun.coolweather.util;

/**
 * Created by wangyun on 15/10/23.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
