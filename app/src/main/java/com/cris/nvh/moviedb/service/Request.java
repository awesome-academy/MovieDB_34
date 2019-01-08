package com.cris.nvh.moviedb.service;

import com.cris.nvh.moviedb.data.model.GenreResponse;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public interface Request {
    @GET("genre/movie/list")
    Observable<GenreResponse> getGenres();

    @GET("trending/movie/day")
    Observable<MovieResponse> getMoviesTrendingByDay();

    @GET("discover/movie")
    Observable<MovieResponse> getMoviesByGenre(@Query("with_genres") String genreId,
                                               @Query("page") int page);

    @GET("discover/movie")
    Observable<MovieResponse> getMoviesByCast(@Query("with_cast") String castId,
                                              @Query("page") int page);

    @GET("discover/movie")
    Observable<MovieResponse> getMoviesByCompany(@Query("with_companies") String CompanyId,
                                                 @Query("page") int page);

    @GET("movie/{type}")
    Observable<MovieResponse> getMoviesCategory(@Path("type") String type,
                                                @Query("page") int page);

    @GET("movie/{movie_id}")
    Observable<Movie> getMovieDetail(@Path("movie_id") int id,
                                     @Query("append_to_response") String value);

    @GET("search/{type}")
    Observable<MovieResponse> searchMovie(@Path("type") String type,
                                          @Query("query") String keyword,
                                          @Query("page") int page);
}
