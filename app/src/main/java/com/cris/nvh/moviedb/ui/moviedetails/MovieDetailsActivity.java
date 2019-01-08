package com.cris.nvh.moviedb.ui.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.adapter.MoviePagerAdapter;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.repository.MovieRepository;
import com.cris.nvh.moviedb.data.source.local.LocalDataSource;
import com.cris.nvh.moviedb.data.source.remote.RemoteDataSource;
import com.cris.nvh.moviedb.databinding.ActivityMovieDetailBinding;
import com.cris.nvh.moviedb.ui.cast.CastFragment;
import com.cris.nvh.moviedb.ui.info.InfoFragment;
import com.cris.nvh.moviedb.ui.producer.ProducerFragment;
import com.cris.nvh.moviedb.ui.trailer.TrailerFragment;

import static com.cris.nvh.moviedb.ui.home.HomeFragment.EXTRA_MOVIE;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class MovieDetailsActivity extends AppCompatActivity {
    private static final int DURATION = 1000;
    private static final int FROMALPHA = 0;
    private static final int TOALPHA = 1;
    private VideoFragment mYoutubePlayerFragment;
    private ActivityMovieDetailBinding mBinding;
    private MovieDetailsViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        initViewModel();
        mBinding.setMovieVM(mViewModel);
        initView();
    }

    private void initViewModel() {
        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        MovieRepository movieRepository = MovieRepository.getInstance(
                LocalDataSource.getInstance(),
                RemoteDataSource.getInstance());
        mViewModel = new MovieDetailsViewModel(movie.getId(), movieRepository);
    }

    public static Intent getIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    private void initView() {
        mYoutubePlayerFragment = (VideoFragment) getSupportFragmentManager().findFragmentById(R.id.player);
        initFragment();
        mBinding.tabLayout.setAnimation(alphaAnimation());
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager, true);
    }

    private void initFragment() {
        InfoFragment infoFragment = InfoFragment.newInstance();
        infoFragment.setViewModel(mViewModel);
        TrailerFragment trailerFragment = TrailerFragment.newInstance();
        CastFragment castFragment = CastFragment.newInstance();
        ProducerFragment produceFragment = ProducerFragment.newInstance();
        MoviePagerAdapter pagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(infoFragment, getString(R.string.title_information));
        pagerAdapter.addFragment(trailerFragment, getString(R.string.title_trailer));
        pagerAdapter.addFragment(produceFragment, getString(R.string.title_producer));
        pagerAdapter.addFragment(castFragment, getString(R.string.title_actor));
        mBinding.viewPager.setAdapter(pagerAdapter);
    }

    public Animation alphaAnimation() {
        Animation alphaAnimation = new AlphaAnimation(FROMALPHA, TOALPHA);
        alphaAnimation.setDuration(DURATION);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        return alphaAnimation;
    }
}