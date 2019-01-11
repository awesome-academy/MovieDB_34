package com.cris.nvh.moviedb.data.source;

import android.databinding.ObservableArrayList;
import com.cris.nvh.moviedb.data.model.GenreResponse;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.model.MovieResponse;
import io.reactivex.Observable;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public interface MovieDataSource {
    interface Local {
        boolean insertToFavorite(Movie movie);

        boolean deleteFromFavorite(int movieId);

        boolean isFavorite(int movieID);

        ObservableArrayList<Movie> getFavoriteMovies();
    }

    interface Remote {
        Observable<MovieResponse> getMoviesByCategory(String category, int page);

        Observable<MovieResponse> getMoviesByGenre(int genreId, int page);

        Observable<MovieResponse> getMoviesByCast(int castId, int page);

        Observable<MovieResponse> getMoviesByCompany(int companyId, int page);

        Observable<MovieResponse> getMoviesTrendingByDay();

        Observable<Movie> getMovieDetail(int movieId);

        Observable<GenreResponse> getGenres();

        Observable<MovieResponse> searchMovie(String input, int page);
    }
}
