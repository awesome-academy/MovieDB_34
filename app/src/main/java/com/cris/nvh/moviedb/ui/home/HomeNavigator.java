package com.cris.nvh.moviedb.ui.home;

import com.cris.nvh.moviedb.data.model.Movie;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public interface HomeNavigator {
    void startMoviesActivity(String id, String name, int getBy);

    void startMovieDetailActivity(Movie movie);

    void startSearchActivity();
}
