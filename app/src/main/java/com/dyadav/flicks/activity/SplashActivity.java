package com.dyadav.flicks.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dyadav.flicks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.appname) TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Carrington.ttf");
        txt.setTypeface(font);
        txt.setText(R.string.app_name);

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
