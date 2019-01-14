package com.cris.nvh.moviedb.ui.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.v4.view.ViewPager;

import com.cris.nvh.moviedb.data.annotation.GenresKey;
import com.cris.nvh.moviedb.data.model.Genre;
import com.cris.nvh.moviedb.data.model.GenreResponse;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.model.MovieResponse;
import com.cris.nvh.moviedb.data.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class HomeViewModel extends BaseObservable {
    public final ObservableList<Movie> popularMoviesObservable;
    public final ObservableList<Movie> nowPlayingMoviesObservable;
    public final ObservableList<Movie> upComingMoviesObservable;
    public final ObservableList<Movie> topRateMoviesObservable;
    public final ObservableList<Movie> topTrendingMoviesObservable;
    public final ObservableList<Genre> genresObservable;
    public final ObservableList<Movie> genreMoviesObservable;
    public final ObservableBoolean isLoadingSuccess;
    private static final int FIRST_PAGE = 1;
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable;

    public HomeViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        popularMoviesObservable = new ObservableArrayList<>();
        nowPlayingMoviesObservable = new ObservableArrayList<>();
        upComingMoviesObservable = new ObservableArrayList<>();
        topRateMoviesObservable = new ObservableArrayList<>();
        topTrendingMoviesObservable = new ObservableArrayList<>();
        genreMoviesObservable = new ObservableArrayList<>();
        genresObservable = new ObservableArrayList<>();
        isLoadingSuccess = new ObservableBoolean();
        mCompositeDisposable = new CompositeDisposable();
        initData();
    }

    public List<ObservableList<Movie>> getCategoryMovies() {
        List<ObservableList<Movie>> listCategories = new ArrayList<>();
        listCategories.add(popularMoviesObservable);
        listCategories.add(nowPlayingMoviesObservable);
        listCategories.add(upComingMoviesObservable);
        listCategories.add(topRateMoviesObservable);
        return listCategories;
    }

    public void dispose() {
        mCompositeDisposable.dispose();
    }

    private void initData() {
        loadTopTrendingMovies();
        loadPopularMovies();
        loadNowPlayingMovies();
        loadUpComingMovies();
        loadTopRateMovies();
        loadGenre();
    }

    private void loadTopRateMovies() {
        Disposable disposable = mMovieRepository.getMoviesByCategory(GenresKey.TOP_RATED, FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Exception {
                        topRateMoviesObservable.addAll(movieResponse.getMovies());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadUpComingMovies() {
        Disposable disposable = mMovieRepository.getMoviesByCategory(GenresKey.UPCOMING, FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Exception {
                        upComingMoviesObservable.addAll(movieResponse.getMovies());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadNowPlayingMovies() {
        Disposable disposable = mMovieRepository.getMoviesByCategory(GenresKey.NOW_PLAYING, FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Exception {
                        nowPlayingMoviesObservable.addAll(movieResponse.getMovies());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(throwable.getMessage());
                    }

                });
        mCompositeDisposable.add(disposable);
    }

    private void loadPopularMovies() {
        Disposable disposable = mMovieRepository.getMoviesByCategory(GenresKey.POPULAR, FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Exception {
                        popularMoviesObservable.addAll(movieResponse.getMovies());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadTopTrendingMovies() {
        Disposable disposable = mMovieRepository.getMoviesTrendingByDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Exception {
                        topTrendingMoviesObservable.addAll(movieResponse.getMovies());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadGenre() {
        Disposable disposable = mMovieRepository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GenreResponse>() {
                    @Override
                    public void accept(GenreResponse genreResponse) throws Exception {
                        genresObservable.addAll(genreResponse.getGenres());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void handleError(String message) {
    }


}
