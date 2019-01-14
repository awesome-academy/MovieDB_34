package com.cris.nvh.moviedb.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.adapter.SlideAdapter;
import com.cris.nvh.moviedb.data.repository.MovieRepository;
import com.cris.nvh.moviedb.data.source.local.LocalDataSource;
import com.cris.nvh.moviedb.data.source.remote.RemoteDataSource;
import com.cris.nvh.moviedb.databinding.FragmentHomeBinding;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class HomeFragment extends Fragment {
    private HomeViewModel mHomeViewModel;
    private FragmentHomeBinding mHomeBinding;
    private ViewPager mViewPager;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        initViewModel();
        mHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                container, false);
        mHomeBinding.setHomeVM(mHomeViewModel);
        mHomeBinding.pagerImages.setAdapter(new SlideAdapter());
        mHomeBinding.indicator.setupWithViewPager(mHomeBinding.pagerImages, true);
        return mHomeBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomeViewModel.dispose();
    }

    private void initViewModel() {
        MovieRepository movieRepository = MovieRepository.getInstance(
                LocalDataSource.getInstance(),
                RemoteDataSource.getInstance());
        mHomeViewModel = new HomeViewModel(movieRepository);
    }
}
