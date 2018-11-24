package com.icbc.nafiza.sanjay.icbc.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.util.DBHelper;

public class SplashActivity extends AppCompatActivity {

    DBHelper dbHelper;
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_activity);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);


        progressBar.getIndeterminateDrawable().setColorFilter(
                Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, SignInActivity.class);

                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);


        dbHelper = new DBHelper(this);
        dbHelper.createTables();


    }


}
