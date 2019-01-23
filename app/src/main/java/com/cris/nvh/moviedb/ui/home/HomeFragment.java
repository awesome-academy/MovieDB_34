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
import com.cris.nvh.moviedb.adapter.CategoryAdapter;
import com.cris.nvh.moviedb.adapter.CategoryAdapter.CategoryViewHolder.MoviesAdapter;
import com.cris.nvh.moviedb.adapter.SlideAdapter;
import com.cris.nvh.moviedb.data.annotation.CategoryRequest;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.repository.MovieRepository;
import com.cris.nvh.moviedb.data.source.local.LocalDataSource;
import com.cris.nvh.moviedb.data.source.remote.RemoteDataSource;
import com.cris.nvh.moviedb.databinding.FragmentHomeBinding;
import com.cris.nvh.moviedb.ui.moviedetails.MovieDetailsActivity;
import com.cris.nvh.moviedb.ui.movies.MoviesActivity;

import static com.cris.nvh.moviedb.ui.search.SearchActivity.getIntent;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class HomeFragment extends Fragment implements HomeNavigator, SlideAdapter.TopTrendingClickListener,
        ViewPager.OnPageChangeListener, MoviesAdapter.MovieItemClickListener,
        CategoryAdapter.CategoryClickListener {
    private HomeViewModel mHomeViewModel;
    private FragmentHomeBinding mHomeBinding;
    private SlideAdapter mAdapter;

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
        mAdapter = new SlideAdapter(this);
        mHomeBinding.pagerImages.setAdapter(mAdapter);
        mHomeBinding.indicator.setupWithViewPager(mHomeBinding.pagerImages, true);
        mHomeBinding.recyclerCategories.setAdapter(new CategoryAdapter(this));
        mHomeBinding.recyclerGenres.setAdapter(new MoviesAdapter(this));
        return mHomeBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomeViewModel.dispose();
    }

    @Override
    public void startMoviesActivity(String id, int getBy) {
        startActivity(MoviesActivity.getIntent(getActivity()));
    }

    @Override
    public void startMovieDetailActivity(Movie movie) {
        startActivity(MovieDetailsActivity.getIntent(getActivity()));
    }

    @Override
    public void startSearchActivity() {
        startActivity(getIntent(getActivity()));
    }

    @Override
    public void onTopTrendingClick(Movie movie) {
        startMovieDetailActivity(movie);
    }

    @Override
    public void onCategoryClick(String type) {
        startMoviesActivity(type, CategoryRequest.CATEGORY);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
        mAdapter.setCurrentPosition(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    @Override
    public void onMovieItemClick(Movie movie) {
        startMovieDetailActivity(movie);
    }

    private void initViewModel() {
        MovieRepository movieRepository = MovieRepository.getInstance(
                LocalDataSource.getInstance(),
                RemoteDataSource.getInstance());
        mHomeViewModel = new HomeViewModel(this, movieRepository);
    }
}
