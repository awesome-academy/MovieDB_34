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
import com.cris.nvh.moviedb.ui.favorite.FavoriteFragment;
import com.cris.nvh.moviedb.ui.home.HomeFragment;
import com.cris.nvh.moviedb.ui.setting.SettingFragment;

public class MainActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener,
        FavoriteFragment.RefreshHomeData, HomeFragment.NeedToRefreshListener {
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
        HomeFragment homeFragment = HomeFragment.newInstance();
        homeFragment.setListener(this);
        adapter.addFragment(homeFragment);
        FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();
        favoriteFragment.setListener(this);
        adapter.addFragment(favoriteFragment);
        SettingFragment settingFragment = SettingFragment.newInstance();
        adapter.addFragment(settingFragment);
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

    private void refreshFavoriteData() {
        ViewPagerAdapter adapter = (ViewPagerAdapter) mViewpager.getAdapter();
        FavoriteFragment favoriteFragment = (FavoriteFragment) adapter.getItem(FAVORITE_INDEX);
        favoriteFragment.refreshMovies();
    }

    @Override
    public void onRefreshHomeFragment() {
        ViewPagerAdapter adapter = (ViewPagerAdapter) mViewpager.getAdapter();
        HomeFragment homeFragment = (HomeFragment) adapter.getItem(HOME_INDEX);
        homeFragment.updateFavoriteMovie();
    }

    @Override
    public void refreshFavoriteFragment() {
        refreshFavoriteData();
    }
}
