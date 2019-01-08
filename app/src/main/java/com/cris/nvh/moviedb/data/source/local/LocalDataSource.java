package com.cris.nvh.moviedb.data.source.local;

import android.databinding.ObservableArrayList;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.source.MovieDataSource;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class LocalDataSource implements MovieDataSource.Local {
    private static LocalDataSource sLocal;

    public static LocalDataSource getInstance() {
        if (sLocal == null)
            sLocal = new LocalDataSource();
        return sLocal;
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
    public ObservableArrayList<Movie> getFavoriteMovies() {
        return null;
    }
}
