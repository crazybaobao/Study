package com.example.wangyun.fuckj;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

/**
 * Created by wangyun on 2016/12/20.
 */
public class AutoCompleteTextViewTest extends Activity {
    AutoCompleteTextView actv;
    MultiAutoCompleteTextView mauto;

    String[] books = new String[]{
            "疯狂Java讲义",
            "疯狂Ajax讲义", "疯狂XML讲义", "疯狂Workflow讲义"
    };

    int[] images = new int[]{
            R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5
    };
    int currentImg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout
                .simple_dropdown_item_1line, books);
        actv = (AutoCompleteTextView) findViewById(R.id.auto);
        mauto = (MultiAutoCompleteTextView) findViewById(R.id.mauto);
        actv.setAdapter(aa);
        mauto.setAdapter(aa);
        mauto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        final ImageView image =(ImageView)findViewById(R.id.im);
        image.setImageResource(images[0]);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(images[++currentImg%images.length]);
            }
        });

        final Chronometer ch=(Chronometer)findViewById(R.id.test);
        final Button start=(Button)findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch.setBase(SystemClock.elapsedRealtime());
                ch.start();
                start.setEnabled(false);
            }
        });
        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(SystemClock.elapsedRealtime()-ch.getBase()>10000)
                {ch.stop();
                start.setEnabled(true);}
            }
        });
    }
}
