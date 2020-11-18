package com.example.tepitoflix;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public final class MovieContract {
    private MovieContract() {}

    public static class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "Movie";
        public static final int DATABASE_VERSION =1;

        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String GENRE = "genre";
        public static final String LENGTH = "length";
        public static final String DIRECTOR = "director";
        public static final String YEAR = "year";
        public static final String PRICE = "price";
    }

    public static class SerieEntry implements BaseColumns {
        public static String TABLE_NAME = "Serie";

        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String GENRE = "genre";
        public static final String DIRECTOR = "director";
        public static final String YEAR = "year";
        public static final String PRICE = "price";
    }

    public static class CDEntry implements BaseColumns {
        public static String TABLE_NAME = "CD";

        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String GENRE = "genre";
        public static final String ARTIST = "artist";
        public static final String YEAR = "year";
        public static final String PRICE = "price";
    }

    public static class ArtistEntry implements BaseColumns {
        public static String TABLE_NAME = "Artist";

        public static final String ID = "id";
        public static final String NAME = "name";
    }

    public static class GenreEntry implements BaseColumns {
        public static String TABLE_NAME = "Genre";

        public static final String ID = "id";
        public static final String NAME = "name";
    }

    public static class DirectorEntry implements BaseColumns {
        public static String TABLE_NAME = "Director";

        public static final String ID = "id";
        public static final String NAME = "name";
    }
}

