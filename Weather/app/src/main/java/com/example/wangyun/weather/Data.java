package com.example.wangyun.weather;

import java.net.URLEncoder;

/**
 * Created by wangyun on 15/12/16.
 * 拼两个连接，一个用于获取WOEID，一个用于获取天气
 */
public class Data {
    public static String WOEID = "";
    public String city = "";
    public static String Weather = "";

    public void setWOEID(String city) {
        //传入城市参数，拼一个获取WOEID的连接地址
        this.city = city;
        WOEID = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20geo" +
                ".places%20where%20text%3D%22" + URLEncoder.encode(city) +
                "+%22&diagnostics=true";
    }

    public static String getWOEID() {
        return WOEID;
    }

    public static void setWeather() {
        //拼连接
        Weather="https://query.yahooapis.com/v1/public/yql?q=select%20item.condition%20from%20weather.forecast%20where%20woeid%20%3D%20"+2487889+"&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
    }

    public static String getWeather() {
        return Weather;
    }
}
