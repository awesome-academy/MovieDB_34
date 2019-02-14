package com.cris.nvh.moviedb.data.source.local;

import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.source.MovieDataSource;

import java.util.List;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class LocalDataSource implements MovieDataSource.Local {
    private static LocalDataSource sLocal;
    private FavoriteReaderDBHelper mDBHelper;

    private LocalDataSource(FavoriteReaderDBHelper dbHelper) {
        mDBHelper = dbHelper;
    }

    public static LocalDataSource getInstance(FavoriteReaderDBHelper dbHelper) {
        if (sLocal == null)
            sLocal = new LocalDataSource(dbHelper);
        return sLocal;
    }

    @Override
    public boolean insertToFavorite(Movie movie) {
        return mDBHelper.addToFavorite(movie);
    }

    @Override
    public boolean deleteFromFavorite(int movieId) {
        return mDBHelper.removeFromFavorite(movieId);
    }

    @Override
    public boolean isFavorite(int movieID) {
        return mDBHelper.isFavoriteMovie(movieID);
    }

    @Override
    public List<Movie> getFavoriteMovies() {
        return mDBHelper.getMovies();
    }
}
