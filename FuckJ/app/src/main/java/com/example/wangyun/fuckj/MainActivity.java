package com.example.wangyun.fuckj;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wangyun.fuckj.util.HttpUtil;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int currentColor = 0;
    final int[] colors = new int[]{
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6,
    };
    final int[] names = new int[]{
            R.id.view01,
            R.id.view02,
            R.id.view03,
            R.id.view04,
            R.id.view05,
            R.id.view06,
    };
    TextView[] views = new TextView[names.length];
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                for (int i = 0; i < names.length; i++) {
                    views[i].setBackgroundResource(colors[(i + currentColor) % names.length]);
                }
                currentColor++;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AutoCompleteTextViewTest.class);
                startActivity(intent);
            }
        });

        for (int i = 0; i < names.length; i++) {
            views[i] = (TextView) findViewById(names[i]);
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        }, 0, 3000);

        RelativeLayout root = (RelativeLayout) findViewById(R.id.root);
        final DrawView draw = new DrawView(this);
/*        draw.setMinimumWidth(3);
        draw.setMinimumHeight(5);*/
        root.addView(draw);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hello_world, menu);
        return true;
    }

    public void clickHandler(View source) {
        TextView tv = (TextView) findViewById(R.id.show);
        //tv.setText(getString(R.string.time) + new Date());
        try {
            String a = HttpUtil.getRequest(HttpUtil.BSAE_URL);
            tv.setText(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
