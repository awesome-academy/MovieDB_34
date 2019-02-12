package com.cris.nvh.moviedb.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public final class FavoriteReaderContract {

    public static class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_NAME_MOVIE_ID = "id_track";
        public static final String COLUMN_NAME_BACK_DROP_PATH = "back_drop_path";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_POSTER_PATH = "poster_path";
        public static final String COLUMN_NAME_VOTE_AVERAGE = "vote_average";
    }
}
