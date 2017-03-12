package com.dyadav.flicks.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dyadav.flicks.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView txt = (TextView) findViewById(R.id.appname);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Carrington.ttf");
        txt.setTypeface(font);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MovieActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}
