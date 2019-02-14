package com.cris.nvh.moviedb.ui.moviedetails;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public interface MovieDetailNavigator {
    void startSearchActivity();
    void onBackPress();
    void startMoviesActivity(int type, String name, String id);
}
