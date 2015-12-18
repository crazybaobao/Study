package com.example.wangyun.weather;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

/**
 * Created by wangyun on 15/12/17.
 * 解析XML格式的response
 */
public class ParseXMLWithPull {
    public String woeid;

    public String parseXMLWithPull(String xmlData) {
        try {
            //获取XmlPullParserFactory的实例
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //用这个实例再去创建XmlPullParser对象
            XmlPullParser xmlPullParser = factory.newPullParser();
            //把得到的response放进去开始进行解析
            xmlPullParser.setInput(new StringReader(xmlData));
            //通过getEventType()得到当前的解析事件
            int eventType = xmlPullParser.getEventType();

            //如果没到文件末尾，则继续解析
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //获取当前结点名字
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //开始解析某个结点
                    case XmlPullParser.START_TAG: {
                        if ("woeid".equals(nodeName)) {
                            woeid = xmlPullParser.nextText();
                            break;
                        }
                    }
                    //完成解析某个结点
                    case XmlPullParser.END_TAG: {
                        if ("query".equals(nodeName)) {
                            Log.d("xml", "id is " + woeid);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return woeid;
    }
}
