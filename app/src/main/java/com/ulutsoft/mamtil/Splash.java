package com.ulutsoft.mamtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends Activity implements Animation.AnimationListener {

    ImageView lay1;
    ImageView lay2;
    ImageView lay3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        lay1 = (ImageView)findViewById(R.id.lay1);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        lay1.startAnimation(animation1);

        lay2 = (ImageView)findViewById(R.id.lay2);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        lay2.startAnimation(animation2);

        lay3 = (ImageView)findViewById(R.id.lay3);
        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_lay3);
        lay3.startAnimation(animation3);

        animation3.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
