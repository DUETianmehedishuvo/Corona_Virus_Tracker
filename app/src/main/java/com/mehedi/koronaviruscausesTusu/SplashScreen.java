package com.mehedi.koronaviruscausesTusu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class SplashScreen extends AppCompatActivity {

    private Animation animation;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        relativeLayout=findViewById(R.id.relativelayout);


//        Thread background=new Thread(){
//            public void run() {
//
//                try {
//                    sleep(5 * 1000);
//                    startActivity(new Intent(getBaseContext(), MainActivity.class));
//                    finish();
//                } catch (Exception e) {
//
//                }
//            }
//        };
//        background.start();


        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getBaseContext(), FirstActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        relativeLayout.startAnimation(animation);

    }
}
