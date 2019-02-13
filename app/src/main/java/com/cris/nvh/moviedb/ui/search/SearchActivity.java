package com.cris.nvh.moviedb.ui.search;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.adapter.CategoryAdapter.CategoryViewHolder.MoviesAdapter;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.repository.MovieRepository;
import com.cris.nvh.moviedb.data.source.local.FavoriteReaderDBHelper;
import com.cris.nvh.moviedb.data.source.local.LocalDataSource;
import com.cris.nvh.moviedb.data.source.remote.RemoteDataSource;
import com.cris.nvh.moviedb.databinding.ActivitySearchBinding;
import com.cris.nvh.moviedb.ui.moviedetails.MovieDetailsActivity;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class SearchActivity extends AppCompatActivity implements MoviesAdapter.MovieItemClickListener,
        SearchNavigator {
    private ActivitySearchBinding mBinding;
    private SearchViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        initViewModel();
        mBinding.setSearchVM(mViewModel);
        mBinding.recyclerSearch.setAdapter(new MoviesAdapter(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.updateFavoriteMovie();
    }

    @Override
    public void onMovieItemClick(Movie movie) {
        startMovieDetailActivity(movie);
    }

    @Override
    public void onFavoriteImageClick(Movie movie) {
        mViewModel.onFavoriteImageClick(movie);
    }

    @Override
    public void startMovieDetailActivity(Movie movie) {
        startActivity(MovieDetailsActivity.getIntent(this, movie));
    }

    @Override
    public void onBackPress() {
        onBackPressed();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    private void initViewModel() {
        FavoriteReaderDBHelper dbHelper = new FavoriteReaderDBHelper(this);
        MovieRepository movieRepository = MovieRepository.getInstance(
                LocalDataSource.getInstance(dbHelper),
                RemoteDataSource.getInstance());
        mViewModel = new SearchViewModel(movieRepository);
        mViewModel.setNavigator(this);
    }
}
