package com.cris.nvh.moviedb.ui.moviedetails;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;

import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.repository.MovieRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class MovieDetailsViewModel extends BaseObservable {
    public final ObservableField<Movie> movieObservable;
    public final ObservableBoolean isFavoriteMovieObservable;
    public final ObservableBoolean isLoadingSuccess;
    private final ObservableInt currentVideoIndex;
    private final ObservableBoolean isPlaying;
    private OnChangeVideoListener mListener;
    private MovieRepository mMovieRepository;
    private MovieDetailNavigator mNavigator;
    private static final String APPEND_TO_MOVIE_DETAIL = "videos,credits";
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MovieDetailsViewModel(int movieId, MovieRepository movieRepository) {
        movieObservable = new ObservableField<>();
        isFavoriteMovieObservable = new ObservableBoolean();
        isLoadingSuccess = new ObservableBoolean();
        isPlaying = new ObservableBoolean();
        currentVideoIndex = new ObservableInt();
        mMovieRepository = movieRepository;
        loadMovie(movieId);
    }

    public void setNavigator(MovieDetailNavigator navigator) {
        mNavigator = navigator;
    }

    public void startSearchActivity() {
        mNavigator.startSearchActivity();
    }

    public void onBackPress(){
        mNavigator.onBackPress();
    }

    public void setOnChangeVideoListener(OnChangeVideoListener listener) {
        mListener = listener;
    }

    public void clear() {
        mCompositeDisposable.dispose();
    }

    private void loadMovie(final int movieId) {
        Disposable disposable = mMovieRepository.getMovieDetail(movieId, APPEND_TO_MOVIE_DETAIL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Movie>() {
                    @Override
                    public void accept(Movie movie) {
                        movieObservable.set(movie);
                        if (movie.getVideoResponse().getVideos().size() > 0) {
                            mListener.setVideoKey(
                                    movie.getVideoResponse().getVideos().get(0).getKey());
                            currentVideoIndex.set(0);
                            isPlaying.set(mListener.isPlaying());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
