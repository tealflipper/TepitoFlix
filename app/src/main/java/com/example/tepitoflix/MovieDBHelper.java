package com.example.tepitoflix;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MovieDBHelper extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 1;
    public static final String DATABSE_NAME = "Movie.db";
    MovieContract.MovieEntry MovieEntry = new MovieContract.MovieEntry();
    MovieContract.ArtistEntry ArtistEntry = new MovieContract.ArtistEntry();
    MovieContract.CDEntry CDEntry = new MovieContract.CDEntry();
    MovieContract.DirectorEntry DirectorEntry = new MovieContract.DirectorEntry();
    MovieContract.GenreEntry GenreEntry = new MovieContract.GenreEntry();
    MovieContract.SerieEntry SerieEntry = new MovieContract.SerieEntry();

    private final String CREATE_MOVIE =
            "CREATE TABLE " + MovieEntry.TABLE_NAME + " ( "+
            MovieEntry.ID + " TEXT PRIMARY KEY, " +
            MovieEntry.TITLE + " TEXT, " +
            MovieEntry.GENRE + " TEXT, "+
            MovieEntry.LENGTH + " INTEGER, " +
            MovieEntry.DIRECTOR + " TEXT, " +
            MovieEntry.YEAR + " INTEGER, " +
            MovieEntry.PRICE + " NUMERIC (4,2) , "+
            "FOREIGN KEY( '"+MovieEntry.DIRECTOR+"' ) REFERENCES '"+DirectorEntry.TABLE_NAME+ "' ( id ), "+
            "FOREIGN KEY( 'genre' ) REFERENCES  'Genre' ( 'id' ))";

    private final String CREATE_CD =
            "CREATE TABLE " + CDEntry.TABLE_NAME + " ( "+
                    CDEntry.ID + " TEXT PRIMARY KEY, " +
                    CDEntry.TITLE + " TEXT, " +
                    CDEntry.GENRE + " TEXT, "+
                    CDEntry.ARTIST+ " TEXT, " +
                    CDEntry.YEAR + " INTEGER, " +
                    CDEntry.PRICE + " NUMERIC (4,2) , "+
                    " FOREIGN KEY('artist') REFERENCES 'Artist'('id'), "+
                    " FOREIGN KEY('genre') REFERENCES 'Genre'('id'))";

    private final String CREATE_SERIE =
            "CREATE TABLE " + SerieEntry.TABLE_NAME + " ( "+
                    SerieEntry.ID + " TEXT PRIMARY KEY, " +
                    SerieEntry.TITLE + " TEXT, " +
                    SerieEntry.GENRE + " TEXT, "+
                    SerieEntry.DIRECTOR + " TEXT, " +
                    SerieEntry.YEAR + " INTEGER, " +
                    SerieEntry.PRICE + " NUMERIC (4,2) , " +
                    "FOREIGN KEY( "+ SerieEntry.DIRECTOR+" ) REFERENCES "+DirectorEntry.TABLE_NAME+
                    "(" +DirectorEntry.ID+" ), FOREIGN KEY( "+SerieEntry.GENRE+" ) REFERENCES "+
                    GenreEntry.TABLE_NAME+"( "+GenreEntry.ID+ " ))";

    private final String CREATE_ARTIST =
            "CREATE TABLE " + ArtistEntry.TABLE_NAME + " ( "+
            ArtistEntry.ID + " TEXT PRIMARY KEY, "+
            ArtistEntry.NAME + " TEXT )";

    private final String CREATE_DIRECTOR =
            "CREATE TABLE " + DirectorEntry.TABLE_NAME + " ( "+
                    DirectorEntry.ID + " TEXT PRIMARY KEY, "+
                    DirectorEntry.NAME + " TEXT )";

    private final String CREATE_GENRE =
            "CREATE TABLE " + GenreEntry.TABLE_NAME + " ( "+
                    GenreEntry.ID + " TEXT PRIMARY KEY, "+
                    GenreEntry.NAME + " TEXT )";

    private final String DELETE_MOVIE =
            "DROP TABLE IF EXIST '" + MovieEntry.TABLE_NAME+"'";

    private final String DELETE_ARTIST =
            "DROP TABLE IF EXIST "+"'" + ArtistEntry.TABLE_NAME+"'";

    private final String DELETE_CD =
            "DROP TABLE IF EXIST "+"'" + CDEntry.TABLE_NAME+"'";

    private final String DELETE_DIRECTOR =
            "DROP TABLE IF EXIST "+"'" + DirectorEntry.TABLE_NAME+"'";

    private final String DELETE_GENRE =
            "DROP TABLE IF EXIST " +"'"+ GenreEntry.TABLE_NAME+"'";

    private final String DELETE_SERIE =
            "DROP TABLE IF EXIST "+"'" + SerieEntry.TABLE_NAME+"'";


    public MovieDBHelper(Context context) {
        super(context, DATABSE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;");
        db.execSQL(CREATE_ARTIST);
        db.execSQL(CREATE_GENRE);
        db.execSQL(CREATE_DIRECTOR);
        db.execSQL(CREATE_SERIE);
        db.execSQL(CREATE_MOVIE);
        db.execSQL(CREATE_CD);

    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.rawQuery(DELETE_MOVIE,null);
        db.execSQL(DELETE_CD);
        db.execSQL(DELETE_SERIE);
        db.execSQL(DELETE_ARTIST);
        db.execSQL(DELETE_DIRECTOR);
        db.execSQL(DELETE_GENRE);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}

