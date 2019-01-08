package com.cris.nvh.moviedb.data.annotation;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        GenresKey.TOP_RATED, GenresKey.NOW_PLAYING,
        GenresKey.POPULAR, GenresKey.UPCOMING
})
public @interface GenresKey {
    String TOP_RATED = "top_rated";
    String NOW_PLAYING = "now_playing";
    String POPULAR = "popular";
    String UPCOMING = "upcoming";
}
