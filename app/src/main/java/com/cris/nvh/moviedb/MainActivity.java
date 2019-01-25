package com.cris.nvh.moviedb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.cris.nvh.moviedb.adapter.ViewPagerAdapter;
import com.cris.nvh.moviedb.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private static final int HOME_INDEX = 0;
    private static final int FAVORITE_INDEX = 1;
    private static final int SETTING_INDEX = 2;
    private ViewPager mViewpager;
    private BottomNavigationView mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewpager = findViewById(R.id.viewpager);
        mBottomNavigation = findViewById(R.id.navigation);
        mViewpager.setOnPageChangeListener(this);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(HomeFragment.newInstance());
        mViewpager.setAdapter(adapter);
        mBottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.tab_home:
                mViewpager.setCurrentItem(HOME_INDEX);
                break;
            case R.id.tab_favorite:
                mViewpager.setCurrentItem(FAVORITE_INDEX);
                break;
            case R.id.tab_setting:
                mViewpager.setCurrentItem(SETTING_INDEX);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
        mBottomNavigation.getMenu().getItem(i).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    public static Intent getMainActivityIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
}
