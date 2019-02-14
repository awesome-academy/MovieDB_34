package com.cris.nvh.moviedb.ui.moviedetails;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

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
    private static final int INDEX_ZERO = 0;
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

    public void updateFavoriteMovie() {
        if (movieObservable.get() != null)
            checkIsFavorite(movieObservable.get().getId());
    }

    public void checkIsFavorite(int movieId) {
        isFavoriteMovieObservable.set(mMovieRepository.isFavorite(movieId));
    }

    public void setNavigator(MovieDetailNavigator navigator) {
        mNavigator = navigator;
    }

    public void startSearchActivity() {
        mNavigator.startSearchActivity();
    }

    public void onBackPress() {
        mNavigator.onBackPress();
    }

    public void setOnChangeVideoListener(OnChangeVideoListener listener) {
        mListener = listener;
    }

    public void setFavoriteMovie(Movie movie) {
        if (!isFavoriteMovieObservable.get()) {
            mMovieRepository.insertToFavorite(movie);
            isFavoriteMovieObservable.set(true);
            return;
        }
        mMovieRepository.deleteFromFavorite(movie.getId());
        isFavoriteMovieObservable.set(false);
    }

    public void clear() {
        mCompositeDisposable.dispose();
    }

    private void loadMovie(final int movieId) {
        checkIsFavorite(movieId);
        Disposable disposable = mMovieRepository.getMovieDetail(movieId, APPEND_TO_MOVIE_DETAIL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Movie>() {
                    @Override
                    public void accept(Movie movie) {
                        movieObservable.set(movie);
                        if (movie.getVideoResponse().getVideos().size() > INDEX_ZERO) {
                            mListener.setVideoKey(
                                    movie.getVideoResponse().getVideos().get(INDEX_ZERO).getKey());
                            currentVideoIndex.set(INDEX_ZERO);
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
