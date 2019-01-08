package com.cris.nvh.moviedb.data.source.remote;

import com.cris.nvh.moviedb.data.model.GenreResponse;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.model.MovieResponse;
import com.cris.nvh.moviedb.data.source.MovieDataSource;
import io.reactivex.Observable;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class RemoteDataSource implements MovieDataSource.Remote {
    private static RemoteDataSource sRemote;

    public static RemoteDataSource getInstance() {
        if (sRemote == null)
            sRemote = new RemoteDataSource();
        return sRemote;
    }

    @Override
    public Observable<MovieResponse> getMoviesByCategory(String category, int page) {
        return null;
    }

    @Override
    public Observable<MovieResponse> getMoviesByGenre(int genreId, int page) {
        return null;
    }

    @Override
    public Observable<MovieResponse> getMoviesByCast(int castId, int page) {
        return null;
    }

    @Override
    public Observable<MovieResponse> getMoviesByCompany(int companyId, int page) {
        return null;
    }

    @Override
    public Observable<MovieResponse> getMoviesTrendingByDay() {
        return null;
    }

    @Override
    public Observable<Movie> getMovieDetail(int movieId) {
        return null;
    }

    @Override
    public Observable<GenreResponse> getGenres() {
        return null;
    }

    @Override
    public Observable<MovieResponse> searchMovie(String input, int page) {
        return null;
    }
}
