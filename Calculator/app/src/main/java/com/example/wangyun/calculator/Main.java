package com.example.wangyun.calculator;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by wangyun on 15/11/30.
 */
public class Main extends Activity{

    //创建XListView对象
    private XListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mListView = (XListView) findViewById(R.id.aaa);// ~~~~~指定布局中的xListView控件~~~~~
    }
}