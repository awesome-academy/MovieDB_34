package com.cris.nvh.moviedb.data.repository;

import android.databinding.ObservableArrayList;
import com.cris.nvh.moviedb.data.model.GenreResponse;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.model.MovieResponse;
import com.cris.nvh.moviedb.data.source.MovieDataSource;
import com.cris.nvh.moviedb.data.source.local.LocalDataSource;
import com.cris.nvh.moviedb.data.source.remote.RemoteDataSource;
import io.reactivex.Observable;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class MovieRepository implements MovieDataSource.Local, MovieDataSource.Remote {
    private static MovieRepository sRepository;
    private LocalDataSource mLocal;
    private RemoteDataSource mRemote;

    private MovieRepository(LocalDataSource local, RemoteDataSource remote) {
        mLocal = local;
        mRemote = remote;
    }

    public static MovieRepository getInstance(LocalDataSource local,
                                              RemoteDataSource remote) {
        if (sRepository == null)
            sRepository = new MovieRepository(local, remote);
        return sRepository;
    }

    @Override
    public boolean insertToFavorite(Movie movie) {
        return false;
    }

    @Override
    public boolean deleteFromFavorite(int movieId) {
        return false;
    }

    @Override
    public boolean isFavorite(int movieID) {
        return false;
    }

    @Override
    public ObservableArrayList<Movie> getFavoriteMovies() {
        return null;
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
