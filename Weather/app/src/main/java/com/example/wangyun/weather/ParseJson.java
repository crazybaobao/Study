package com.example.wangyun.weather;

import org.json.JSONObject;

/**
 * Created by wangyun on 15/12/29.
 * 解析根据woeid查询天气返回的数据，可以获得更新时间、日期、天气、温度
 */
public class ParseJson {
    public String time = "";
    public String date = "";
    public String text = "";
    public String temp = "";

    public void pj(String jsonData) {
        try {
            JSONObject obj = new JSONObject(jsonData);
            JSONObject query = obj.getJSONObject("query");
            time = query.getString("created");
            JSONObject results = query.getJSONObject("results");
            JSONObject channel = results.getJSONObject("channel");
            JSONObject item = channel.getJSONObject("item");
            JSONObject condition = item.getJSONObject("condition");
            date = condition.getString("date");
            text = condition.getString("text");
            temp = condition.getString("temp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
