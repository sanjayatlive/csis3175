package com.icbc.nafiza.sanjay.icbc.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.icbc.nafiza.sanjay.icbc.R;

public class SplashActiivty extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_activity);
        Animation myanimation = AnimationUtils.loadAnimation(this,R.anim.splashanimation);



        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);


        progressBar.getIndeterminateDrawable().setColorFilter(
                Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);

    //    progressBar.setBackgroundResource(R.color.icon);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                ActivityOptionsCompat options ;
                int x=20;
                int y=20;

               Intent i = new Intent(SplashActiivty.this, ChoiceActivity.class);

                 startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);


    }
}
