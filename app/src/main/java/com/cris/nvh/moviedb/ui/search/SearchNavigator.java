package com.cris.nvh.moviedb.ui.search;

import com.cris.nvh.moviedb.data.model.Movie;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public interface SearchNavigator {
    void startMovieDetailActivity(Movie movie);
    void onBackPress();
}
