package com.cris.nvh.moviedb.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cris.nvh.moviedb.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class FavoriteReaderDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MovieDB.db";
    private static final String LIKE_ARG = " LIKE ?";
    private static final String QUESTION_ARG = " = ?";
    private static final String OPEN_PARENTHESIS = " (";
    private static final String CLOSE_PARENTHESIS = ")";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA = ",";
    private static final String DROP_IF_EXIST = "DROP TABLE IF EXISTS ";

    private static final String SQL_CREATE_FAVORITE_ENTRY =
            "CREATE TABLE " + FavoriteReaderContract.FavoriteEntry.TABLE_NAME + OPEN_PARENTHESIS +
                    FavoriteReaderContract.FavoriteEntry._ID + INTEGER_TYPE + PRIMARY_KEY + COMMA +
                    FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID + TEXT_TYPE + COMMA +
                    FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA +
                    FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_BACK_DROP_PATH + TEXT_TYPE + COMMA +
                    FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_POSTER_PATH + TEXT_TYPE + COMMA +
                    FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_VOTE_AVERAGE + TEXT_TYPE +
                    CLOSE_PARENTHESIS;

    private static final String SQL_DELETE_FAVORITE_ENTRY =
            DROP_IF_EXIST + FavoriteReaderContract.FavoriteEntry.TABLE_NAME;

    public FavoriteReaderDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_FAVORITE_ENTRY);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public List<Movie> getMovies() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] projection = {
                FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID,
                FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_TITLE,
                FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_BACK_DROP_PATH,
                FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_POSTER_PATH,
                FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_VOTE_AVERAGE,
        };
        Cursor cursor = sqLiteDatabase.query(
                FavoriteReaderContract.FavoriteEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        List<Movie> movies = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID));
            String title = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_TITLE));
            String backDropPath = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_BACK_DROP_PATH));
            String voteAverage = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_VOTE_AVERAGE));
            Movie movie = new Movie(Integer.valueOf(id));
            movie.setBackdropPath(backDropPath);
            movie.setTitle(title);
            movie.setVoteAverage(Float.valueOf(voteAverage));
            movies.add(movie);
        }
        sqLiteDatabase.close();
        return movies;
    }

    public boolean isFavoriteMovie(int movieId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] projection = {FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID,};
        String selection = FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID + QUESTION_ARG;
        String[] selectionArgs = {String.valueOf(movieId)};
        Cursor cursor = sqLiteDatabase.query(
                FavoriteReaderContract.FavoriteEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);
        return cursor.getCount() > 0;
    }

    public boolean removeFromFavorite(int movieId) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID + LIKE_ARG;
        String[] selectionArgs = {String.valueOf(movieId)};
        int deletedRows = db.delete(
                FavoriteReaderContract.FavoriteEntry.TABLE_NAME,
                selection,
                selectionArgs);
        db.close();
        return deletedRows > 0;
    }

    public boolean addToFavorite(Movie movie) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID, movie.getId());
        values.put(FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_TITLE, movie.getTitle());
        values.put(FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_BACK_DROP_PATH, movie.getBackdropPath());
        values.put(FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_POSTER_PATH, movie.getPosterPath());
        values.put(FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_VOTE_AVERAGE, movie.getVoteAverage());
        long id = db.insert(FavoriteReaderContract.FavoriteEntry.TABLE_NAME,
                null, values);
        db.close();
        return id > 0;
    }
}
