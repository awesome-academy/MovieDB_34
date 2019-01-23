package com.cris.nvh.moviedb.data.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        CategoryRequest.CATEGORY, CategoryRequest.GENRE,
        CategoryRequest.CAST, CategoryRequest.COMPANY
})
public @interface CategoryRequest {
    int CATEGORY = 0;
    int GENRE = 1;
    int CAST = 2;
    int COMPANY = 3;
    int TRENDING = 4;
}
