package com.example.wangyun.weather;

/**
 * Created by wangyun on 15/12/16.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}