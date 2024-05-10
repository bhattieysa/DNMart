package com.example.dn_mart;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    Animation top,bottom;
    ImageView image;
    TextView text;
    private static int SPLASH_SCREEN=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //For hide status bar....

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView(R.layout.activity_splash);

        top= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image=findViewById(R.id.appCompatImageView);
        text=findViewById(R.id.text);

        image.setAnimation(top);
        text.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this,Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);





    }
}
