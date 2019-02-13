package com.cris.nvh.moviedb.data.repository;

import com.cris.nvh.moviedb.data.model.GenreResponse;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.model.MovieResponse;
import com.cris.nvh.moviedb.data.source.MovieDataSource;
import com.cris.nvh.moviedb.data.source.local.LocalDataSource;
import com.cris.nvh.moviedb.data.source.remote.RemoteDataSource;

import java.util.List;

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
    public List<Movie> getFavoriteMovies() {
        return mLocal.getFavoriteMovies();
    }

    @Override
    public Observable<MovieResponse> getMoviesByCategory(String category, int page) {
        return mRemote.getMoviesByCategory(category, page);
    }

    @Override
    public Observable<MovieResponse> getMoviesByGenre(String genreId, int page) {
        return mRemote.getMoviesByGenre(genreId, page);
    }

    @Override
    public Observable<MovieResponse> getMoviesByCast(String castId, int page) {
        return mRemote.getMoviesByCast(castId, page);
    }

    @Override
    public Observable<MovieResponse> getMoviesByCompany(String companyId, int page) {
        return mRemote.getMoviesByCompany(companyId, page);
    }

    @Override
    public Observable<MovieResponse> getMoviesTrendingByDay() {
        return mRemote.getMoviesTrendingByDay();
    }

    @Override
    public Observable<Movie> getMovieDetail(int movieId, String value) {
        return mRemote.getMovieDetail(movieId, value);
    }

    @Override
    public Observable<GenreResponse> getGenres() {
        return mRemote.getGenres();
    }

    @Override
    public Observable<MovieResponse> searchMovie(String input, int page) {
        return mRemote.searchMovie(input, page);
    }
}
