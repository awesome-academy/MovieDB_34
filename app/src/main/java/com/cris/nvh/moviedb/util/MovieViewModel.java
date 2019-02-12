package com.cris.nvh.moviedb.util;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.repository.MovieRepository;

public class MovieViewModel extends BaseObservable {
    private Movie mMovie;
    private Context mContext;
    private MovieRepository mMovieRepository;

    public MovieViewModel() {
        mMovie = new Movie();
    }

    public MovieViewModel(Context context) {
        mContext = context;
        mMovie = new Movie();
    }

    public int getId() {
        return mMovie.getId();
    }

    public String getBackdropPath() {
        return mMovie.getBackdropPath();
    }

    public String getPosterPath() {
        return mMovie.getPosterPath();
    }

    public String getTitle() {
        return mMovie.getTitle();
    }

    public String getReleaseDate() {
        return mMovie.getReleaseDate();
    }

    public String getStatus() {
        return mMovie.getStatus();
    }

    public double getVoteAverage() {
        return mMovie.getVoteAverage();
    }

    public String getOverview() {
        return mMovie.getOverview();
    }

    public void setMovie(Movie movie) {
        mMovie = movie;
        notifyChange();
    }

    public Movie getMovie() {
        return mMovie;
    }
}
