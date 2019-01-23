package com.cris.nvh.moviedb.ui.moviedetails;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

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
    private MovieRepository mMovieRepository;
    private static final String APPEND_TO_MOVIE_DETAIL = "videos,credits";
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MovieDetailsViewModel(int movieId, MovieRepository movieRepository) {
        movieObservable = new ObservableField<>();
        isFavoriteMovieObservable = new ObservableBoolean();
        isLoadingSuccess = new ObservableBoolean();
        mMovieRepository = movieRepository;
        loadMovie(movieId);
    }

    private void loadMovie(final int movieId) {
        Disposable disposable = mMovieRepository.getMovieDetail(movieId, APPEND_TO_MOVIE_DETAIL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Movie>() {
                    @Override
                    public void accept(Movie movie) {
                        movieObservable.set(movie);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
