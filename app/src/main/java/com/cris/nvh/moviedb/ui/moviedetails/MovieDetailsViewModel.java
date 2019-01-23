package com.cris.nvh.moviedb.ui.moviedetails;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.repository.MovieRepository;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class MovieDetailsViewModel extends BaseObservable {
    public final ObservableField<Movie> movieObservable;
    public final ObservableBoolean isFavoriteMovieObservable;
    public final ObservableBoolean isLoadingSuccess;
    private MovieRepository mMovieRepository;

    public MovieDetailsViewModel(int movieId, MovieRepository movieRepository) {
        movieObservable = new ObservableField<>();
        isFavoriteMovieObservable = new ObservableBoolean();
        isLoadingSuccess = new ObservableBoolean();
        mMovieRepository = movieRepository;
    }
}
