package com.cris.nvh.moviedb.ui.home;

import com.cris.nvh.moviedb.data.model.Movie;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public interface HomeNavigator {
    public void startMoviesActivity(String id, int getBy);

    public void startMovieDetailActivity(Movie movie);

    public void startSearchActivity();
}
