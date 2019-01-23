package com.cris.nvh.moviedb.ui.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.adapter.MoviePagerAdapter;
import com.cris.nvh.moviedb.ui.cast.CastFragment;
import com.cris.nvh.moviedb.ui.info.InfoFragment;
import com.cris.nvh.moviedb.ui.producer.ProducerFragment;
import com.cris.nvh.moviedb.ui.trailer.TrailerFragment;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class MovieDetailsActivity extends AppCompatActivity {
    private static final int DURATION = 1000;
    private static final int FROMALPHA = 0;
    private static final int TOALPHA = 1;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private VideoFragment mYoutubePlayerFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mYoutubePlayerFragment = (VideoFragment) getSupportFragmentManager().findFragmentById(R.id.player);
        setContentView(R.layout.activity_movie_detail);
        initView();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MovieDetailsActivity.class);
    }

    private void initView() {
        initFragment();
        mTabLayout = findViewById(R.id.tab_layout);
        mYoutubePlayerFragment = (VideoFragment) getSupportFragmentManager().findFragmentById(R.id.player);
        mTabLayout.setAnimation(alphaAnimation());
        mTabLayout.setupWithViewPager(mViewPager, true);
    }

    private void initFragment() {
        mViewPager = findViewById(R.id.view_pager);
        InfoFragment infoFragment = InfoFragment.newInstance();
        TrailerFragment trailerFragment = TrailerFragment.newInstance();
        CastFragment castFragment = CastFragment.newInstance();
        ProducerFragment produceFragment = ProducerFragment.newInstance();
        MoviePagerAdapter pagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(infoFragment, getString(R.string.title_information));
        pagerAdapter.addFragment(trailerFragment, getString(R.string.title_trailer));
        pagerAdapter.addFragment(produceFragment, getString(R.string.title_producer));
        pagerAdapter.addFragment(castFragment, getString(R.string.title_actor));
        mViewPager.setAdapter(pagerAdapter);
    }

    public Animation alphaAnimation() {
        Animation alphaAnimation = new AlphaAnimation(FROMALPHA, TOALPHA);
        alphaAnimation.setDuration(DURATION);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        return alphaAnimation;
    }
}
