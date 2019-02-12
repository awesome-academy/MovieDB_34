package com.cris.nvh.moviedb.ui.favorite;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.repository.MovieRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class FavoriteViewModel {
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable;

    public final ObservableList<Movie> favoriteMoviesObservable;

    public FavoriteViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        mCompositeDisposable = new CompositeDisposable();
        favoriteMoviesObservable = new ObservableArrayList<>();
        loadFavoriteMovies();
    }

    private void loadFavoriteMovies() {
        List<Movie> movies = mMovieRepository.getFavoriteMovies();
        favoriteMoviesObservable.clear();
        favoriteMoviesObservable.addAll(movies);
    }

    public void refreshFavoriteMovies() {
        favoriteMoviesObservable.clear();
        loadFavoriteMovies();
    }

    public boolean deleteFavoriteMovie(int movieId) {
        return mMovieRepository.deleteFromFavorite(movieId);
    }

    public void dispose() {
        mCompositeDisposable.dispose();
    }

    private void handleError(String message) {
    }
}
