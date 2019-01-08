package com.cris.nvh.moviedb.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.cris.nvh.moviedb.R;

import static com.cris.nvh.moviedb.MainActivity.getMainActivityIntent;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class SplashActivity extends AppCompatActivity {
    private static final int TIME_DELAY = 1500;
    private static final int FINAL_POSITION = 80;
    private ImageView mImageLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mImageLogo = findViewById(R.id.image_icon);
        animateImage();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(getMainActivityIntent(SplashActivity.this));
                finish();
            }
        }, TIME_DELAY);
    }

    private void animateImage() {
        SpringAnimation springAnim = new SpringAnimation(mImageLogo,
                DynamicAnimation.TRANSLATION_Y);
        SpringForce springForceX = new SpringForce(FINAL_POSITION);
        springForceX.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springAnim.setSpring(springForceX);
        springAnim.start();
    }
}
