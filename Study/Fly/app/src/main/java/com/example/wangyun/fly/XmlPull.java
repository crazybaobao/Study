package com.example.wangyun.fly;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

/**
 * Created by wangyun on 15/12/17.
 * 从response中解析出woeid
 */
public class XmlPull {

    //String woeid = ""; 不该写在这里，写在这里就是全局变量了，会导致某些逻辑只能运行一次
    public static String xp(String response) {
        String woeid = "";
        try {
            //获取XmlPullParserFactory的实例
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //用这个实例再去创建XmlPullParser对象
            XmlPullParser xmlPullParser = factory.newPullParser();
            //把得到的response放进去开始进行解析
            xmlPullParser.setInput(new StringReader(response));
            //通过getEventType()得到当前的解析事件
            int eventType = xmlPullParser.getEventType();

            //如果没到文件末尾或woeid不为空，则继续解析
            while ((eventType != XmlPullParser.END_DOCUMENT) && woeid.equals("")) {
                //获取当前结点名字
                if (xmlPullParser.getName() != null) {
                    String nodeName = xmlPullParser.getName();
                    switch (eventType) {
                        //开始解析某个结点
                        case XmlPullParser.START_TAG: {
                            if ("woeid".equals(nodeName)) {
                                woeid = xmlPullParser.nextText();
                                Log.d("1", woeid);
                                return woeid;
                            }
                        }
                        //完成解析某个结点
                        case XmlPullParser.END_TAG: {
                            if ("query".equals(nodeName)) {
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    return woeid;}
}
