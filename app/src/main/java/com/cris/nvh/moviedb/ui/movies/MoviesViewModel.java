package com.cris.nvh.moviedb.ui.movies;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.view.View;

import com.cris.nvh.moviedb.data.annotation.CategoryRequest;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.model.MovieResponse;
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

public class MoviesViewModel extends BaseObservable {
    public final ObservableList<Movie> moviesObservable;
    public final ObservableBoolean isLoadingSuccess;
    private int mCurrentPage;
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable;
    private MoviesNavigator mNavigator;

    public MoviesViewModel(int type, String id, MovieRepository repository) {
        moviesObservable = new ObservableArrayList<>();
        mCompositeDisposable = new CompositeDisposable();
        isLoadingSuccess = new ObservableBoolean(true);
        mCurrentPage = 1;
        mMovieRepository = repository;
        loadMovies(type, id);
    }

    public void updateFavoriteMovies() {
        notifyChange();
    }

    public void setNavigator(MoviesNavigator navigator) {
        mNavigator = navigator;
    }

    public void onFavoriteImageClick(Movie movie) {
        int movieId = movie.getId();
        if (mMovieRepository.isFavorite(movieId)) {
            mMovieRepository.deleteFromFavorite(movieId);
            return;
        }
        mMovieRepository.insertToFavorite(movie);
    }

    public void onBackPress(View view) {
        mNavigator.onBackPress();
    }

    public void startSearchActivity(View view) {
        mNavigator.startSearchActivity();
    }

    public void loadMovies(int type, String id) {
        switch (type) {
            case CategoryRequest.GENRE:
                loadMoviesByGenre(id);
                break;
            case CategoryRequest.COMPANY:
                loadMoviesByCompany(id);
                break;
            case CategoryRequest.CAST:
                loadMoviesByCast(id);
                break;
            case CategoryRequest.TRENDING:
                loadMoviesByTrending();
                break;
            case CategoryRequest.CATEGORY:
                break;
            default:
                break;
        }
    }

    private void loadMoviesByTrending() {
        Disposable disposable = mMovieRepository.getMoviesTrendingByDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) {
                        moviesObservable.clear();
                        moviesObservable.addAll(movieResponse.getMovies());
                        isLoadingSuccess.set(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadMoviesByCast(String id) {
        Disposable disposable = mMovieRepository.getMoviesByCast(id, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) {
                        moviesObservable.addAll(movieResponse.getMovies());
                        isLoadingSuccess.set(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadMoviesByCompany(String id) {
        Disposable disposable = mMovieRepository.getMoviesByCompany(id, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) {
                        moviesObservable.addAll(movieResponse.getMovies());
                        isLoadingSuccess.set(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void loadMoviesByCategory(String id) {
        Disposable disposable = mMovieRepository.getMoviesByCategory(id, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) {
                        moviesObservable.addAll(movieResponse.getMovies());
                        isLoadingSuccess.set(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }


    private void loadMoviesByGenre(String id) {
        Disposable disposable = mMovieRepository.getMoviesByGenre(id, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) {
                        moviesObservable.addAll(movieResponse.getMovies());
                        isLoadingSuccess.set(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void dispose() {
        mCompositeDisposable.dispose();
    }

    public void increaseCurrentPage() {
        mCurrentPage++;
    }

    private void handleError(String message) {
    }
}
