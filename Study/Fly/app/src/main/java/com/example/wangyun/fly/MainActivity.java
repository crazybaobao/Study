package com.example.wangyun.fly;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by wangyun on 15/12/17.
 * 输入需要查询的城市，返回该城市woeid
 */

public class MainActivity extends Activity {
    EditText editText;
    Button button;
    TextView textView;
    TextView ttt;
    public String wwwid = "";
    public static final int SHOW_RESPONSE = 0;

    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String rp = (String) msg.obj;
                    textView.setText(rp);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.ed);
        button = (Button) findViewById(R.id.wb);
        ttt = (TextView) findViewById(R.id.tt);
        textView = (TextView) findViewById(R.id.tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendHttpRequest(editText.getText().toString());
            }
        });
    }

    public void sendHttpRequest(final String st) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://query.yahooapis" +
                            ".com/v1/public/yql?q=select%20*%20from%20geo" +
                            ".places%20where%20text%3D%22" + URLEncoder.encode(st) +
                            "%22&diagnostics=true");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    wwwid = XmlPull.xp(response.toString());
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    message.obj = wwwid;
                    handler.sendMessage(message);

                } catch (Exception e) {
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }
        ).start();
    }
}
