package com.cris.nvh.moviedb.ui.moviedetails;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.adapter.MoviePagerAdapter;
import com.cris.nvh.moviedb.data.annotation.CategoryRequest;
import com.cris.nvh.moviedb.data.model.Cast;
import com.cris.nvh.moviedb.data.model.Company;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.repository.MovieRepository;
import com.cris.nvh.moviedb.data.source.local.FavoriteReaderDBHelper;
import com.cris.nvh.moviedb.data.source.local.LocalDataSource;
import com.cris.nvh.moviedb.data.source.remote.RemoteDataSource;
import com.cris.nvh.moviedb.databinding.ActivityMovieDetailBinding;
import com.cris.nvh.moviedb.ui.cast.CastFragment;
import com.cris.nvh.moviedb.ui.info.InfoFragment;
import com.cris.nvh.moviedb.ui.movies.MoviesActivity;
import com.cris.nvh.moviedb.ui.producer.ProducerFragment;
import com.cris.nvh.moviedb.ui.search.SearchActivity;
import com.cris.nvh.moviedb.ui.trailer.TrailerFragment;

import static com.cris.nvh.moviedb.ui.home.HomeFragment.EXTRA_MOVIE;
import static com.cris.nvh.moviedb.ui.movies.MoviesActivity.EXTRA_MOVIES_TITLE;
import static com.cris.nvh.moviedb.ui.movies.MoviesActivity.EXTRA_MOVIES_TYPE;
import static com.cris.nvh.moviedb.ui.movies.MoviesActivity.EXTRA_TYPE_ID;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class MovieDetailsActivity extends AppCompatActivity implements OnChangeVideoListener,
        MovieDetailNavigator, TrailerFragment.OnTrailerSelectedListener,
        ProducerFragment.OnProducerSelectedListener, CastFragment.OnActorSelectedListener {
    private static final int DURATION = 1000;
    private static final int FROMALPHA = 0;
    private static final int TOALPHA = 1;
    private static final int HALF = 2;
    private VideoFragment mYoutubePlayerFragment;
    private ActivityMovieDetailBinding mBinding;
    private MovieDetailsViewModel mViewModel;
    private boolean isDisplayed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        initViewModel();
        mBinding.setMovieVM(mViewModel);
        initView();
        show(mBinding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.updateFavoriteMovie();
    }

    public void show(final View myView) {
        myView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP)
                    return;
                if (!isDisplayed) {
                    int cx = myView.getWidth() / HALF;
                    int h = myView.getHeight();
                    Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cx, 0, h);
                    anim.setDuration(DURATION);
                    anim.start();
                    isDisplayed = true;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.clear();
    }

    @Override
    public void setVideoKey(String videoKey) {
        mYoutubePlayerFragment.setVideoKey(videoKey);
    }

    @Override
    public void playVideo() {
        mYoutubePlayerFragment.playVideo();
    }

    @Override
    public boolean isPlaying() {
        return mYoutubePlayerFragment.isPlaying();
    }

    @Override
    public void startSearchActivity() {
        startActivity(SearchActivity.getIntent(this));
    }

    @Override
    public void onBackPress() {
        onBackPressed();
    }

    @Override
    public void startMoviesActivity(int type, String name, String id) {
        Intent intent = MoviesActivity.getIntent(this);
        intent.putExtra(EXTRA_MOVIES_TYPE, type);
        intent.putExtra(EXTRA_TYPE_ID, id);
        intent.putExtra(EXTRA_MOVIES_TITLE, name);
        startActivity(intent);
    }

    @Override
    public void onTrailerSelected(String videoKey) {
        mYoutubePlayerFragment.setVideoKey(videoKey);
    }

    @Override
    public void onProducerSelected(Company company) {
        startMoviesActivity(CategoryRequest.COMPANY, company.getName(), String.valueOf(company.getId()));
    }

    @Override
    public void onActorSelected(Cast cast) {
        startMoviesActivity(CategoryRequest.CAST, cast.getName(), String.valueOf(cast.getId()));
    }

    private void initViewModel() {
        FavoriteReaderDBHelper dbHelper = new FavoriteReaderDBHelper(this);
        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        MovieRepository movieRepository = MovieRepository.getInstance(
                LocalDataSource.getInstance(dbHelper),
                RemoteDataSource.getInstance());
        mViewModel = new MovieDetailsViewModel(movie.getId(), movieRepository);
        mViewModel.setOnChangeVideoListener(this);
        mViewModel.setNavigator(this);
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
        trailerFragment.setViewModel(mViewModel);
        CastFragment castFragment = CastFragment.newInstance();
        castFragment.setViewModel(mViewModel);
        ProducerFragment produceFragment = ProducerFragment.newInstance();
        produceFragment.setViewModel(mViewModel);
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
