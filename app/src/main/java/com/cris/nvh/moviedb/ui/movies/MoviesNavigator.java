package com.cris.nvh.moviedb.ui.movies;

import com.cris.nvh.moviedb.data.model.Movie;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public interface MoviesNavigator {
    void startMovieDetailActivity(Movie movie);
    void startSearchActivity();
    void onBackPress();
}
