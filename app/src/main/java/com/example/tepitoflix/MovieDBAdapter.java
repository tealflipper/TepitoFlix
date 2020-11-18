package com.example.tepitoflix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.midi.MidiOutputPort;

import java.util.ArrayList;



public class MovieDBAdapter {
    private MovieDBHelper movieDBHelper;
    MovieContract.MovieEntry MovieEntry = new MovieContract.MovieEntry();
    MovieContract.ArtistEntry ArtistEntry = new MovieContract.ArtistEntry();
    MovieContract.CDEntry CDEntry = new MovieContract.CDEntry();
    MovieContract.DirectorEntry DirectorEntry = new MovieContract.DirectorEntry();
    MovieContract.GenreEntry GenreEntry = new MovieContract.GenreEntry();
    MovieContract.SerieEntry SerieEntry = new MovieContract.SerieEntry();
    SQLiteDatabase initDB;

    public MovieDBAdapter(Context context) {
        movieDBHelper = new MovieDBHelper(context);

    }

    private int getMaxId(){
        int i=0;
        int max=0;
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String query = "SELECT MAX(id) AS id FROM "+MovieEntry.TABLE_NAME;
        Cursor cur = sqLiteDatabase.rawQuery(query,null);
        if (cur.getCount() > 0) {
            cur.moveToFirst();
            i = cur.getInt(cur.getColumnIndex("id"));
            max= (i>max)? i:max;
        }

        query = "SELECT MAX(id) AS id FROM "+CDEntry.TABLE_NAME;
        cur = sqLiteDatabase.rawQuery(query,null);
        if (cur.getCount() > 0) {
            cur.moveToFirst();
            i = cur.getInt(cur.getColumnIndex("id"));
            max= (i>max)? i:max;
        }
        query = "SELECT MAX(id) AS id FROM "+SerieEntry.TABLE_NAME;
        cur = sqLiteDatabase.rawQuery(query,null);
        if (cur.getCount() > 0) {
            cur.moveToFirst();
            i = cur.getInt(cur.getColumnIndex("id"));
            max= (i>max)? i:max;
        }
        sqLiteDatabase.close();

        return max;
    }

    public boolean valGenreId(String id){
        String[] columns = {GenreEntry.ID};
        boolean flag=false;
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String query = "SELECT id FROM "+GenreEntry.TABLE_NAME + " WHERE id = '"+ id+"'";
        Cursor cur = sqLiteDatabase.rawQuery(query,null);
        if(cur.getCount()>0){
            flag= true;
        }
        cur.close();

        return flag;
    }

    public boolean valArtistId(String id){
        String[] columns = {ArtistEntry.ID};
        boolean flag=false;
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String query = "SELECT id FROM "+ArtistEntry.TABLE_NAME + " WHERE id = '"+ id+"'";
        Cursor cur = sqLiteDatabase.rawQuery(query,null);
        if(cur.getCount()>0){
            flag= true;
        }
        cur.close();

        return flag;
    }

    public boolean valDirectorId(String id){
        String[] columns = {DirectorEntry.ID};
        boolean flag=false;
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String query = "SELECT id FROM "+DirectorEntry.TABLE_NAME + " WHERE id = '"+ id+"'";
        Cursor cur = sqLiteDatabase.rawQuery(query,null);
        if(cur.getCount()>0){
            flag= true;
        }
        cur.close();

        return flag;
    }

    public long insertMovie(Movie movie)throws SQLException {
        int i = getMaxId()+1;
        movie.setId(""+i);

        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieEntry.ID, movie.getId()); //12 digit long
        contentValues.put(MovieEntry.TITLE, movie.getTitle().toString()); //String
        contentValues.put(MovieEntry.GENRE, movie.getGenre().toString()); //String
        contentValues.put(MovieEntry.LENGTH, movie.getLength()); //3 digit number
        contentValues.put(MovieEntry.DIRECTOR, movie.getDirector()); //String
        contentValues.put(MovieEntry.YEAR, movie.getYear());//int
        contentValues.put(MovieEntry.PRICE, movie.getPrice());// 4 digits 2 decimals

        long id= sqLiteDatabase.insertOrThrow(MovieEntry.TABLE_NAME, null,
                contentValues);
        sqLiteDatabase.close();
        return id;
    }

    public long insertCD(CD cd) throws SQLException {
        int i = getMaxId()+1;
        cd.setId(""+i);

        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CDEntry.ID, cd.getId()); //12 digit long
        contentValues.put(CDEntry.TITLE, cd.getTitle().toString()); //String
        contentValues.put(CDEntry.GENRE, cd.getGenre().toString()); //String
        contentValues.put(CDEntry.ARTIST, cd.getArtist()); //String
        contentValues.put(CDEntry.YEAR, cd.getYear());//int
        contentValues.put(CDEntry.PRICE, cd.getPrice());// 4 digits 2 decimals

        long id= sqLiteDatabase.insertOrThrow(CDEntry.TABLE_NAME, null,
                contentValues);
        sqLiteDatabase.close();
        return id;
    }

    public long insertSerie(Serie serie) throws SQLException {
        int i = getMaxId()+1;
        serie.setId(""+i);
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(SerieEntry.ID, serie.getId());
        contentValues.put(SerieEntry.TITLE, serie.getTitle().toString()); //String
        contentValues.put(SerieEntry.GENRE, serie.getGenre().toString()); //String
        contentValues.put(SerieEntry.DIRECTOR, serie.getDirector()); //String
        contentValues.put(SerieEntry.YEAR, serie.getYear());//int
        contentValues.put(SerieEntry.PRICE, serie.getPrice());// 4 digits 2 decimals

        long id= sqLiteDatabase.insertWithOnConflict(SerieEntry.TABLE_NAME, null,
                contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        sqLiteDatabase.close();
        return id;
    }

    public long insertArtist(Artist artist) throws SQLException {

        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ArtistEntry.ID, artist.getId()); //12 digit long
        contentValues.put(ArtistEntry.NAME, artist.getName().toString()); //String

        long id= sqLiteDatabase.insertWithOnConflict(ArtistEntry.TABLE_NAME, null,
                contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        sqLiteDatabase.close();
        return id;
    }

    public long insertDirector(Director director) throws SQLException {

        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DirectorEntry.ID, director.getId()); //12 digit long
        contentValues.put(DirectorEntry.NAME, director.getName().toString()); //String

        long id= sqLiteDatabase.insertWithOnConflict(DirectorEntry.TABLE_NAME, null,
                contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        sqLiteDatabase.close();
        return id;
    }

    public long insertGenre(Genre genre) throws SQLException {

        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(GenreEntry.ID, genre.getId()); //12 digit long
        contentValues.put(GenreEntry.NAME, genre.getName().toString()); //String

        long id= sqLiteDatabase.insertWithOnConflict(GenreEntry.TABLE_NAME, null,
                contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        sqLiteDatabase.close();
        return id;
    }

    public ArrayList<Movie> getMovies(){
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String[] columns = {
                MovieEntry.ID,
                MovieEntry.TITLE,
                MovieEntry.GENRE,
                MovieEntry.LENGTH,
                MovieEntry.DIRECTOR,
                MovieEntry.YEAR,
                MovieEntry.PRICE};
        Cursor cursor = sqLiteDatabase.query(MovieEntry.TABLE_NAME, columns,null,
                null,null,null,null);
        ArrayList <Movie> movies = new ArrayList<>();

        while (cursor.moveToNext()){
            Movie movie = new Movie();
            movie.setId(cursor.getString(cursor.getColumnIndex(MovieEntry.ID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(MovieEntry.TITLE)));
            movie.setGenre(cursor.getString(cursor.getColumnIndex(MovieEntry.GENRE)));
            movie.setLength(cursor.getInt(cursor.getColumnIndex(MovieEntry.LENGTH)));
            movie.setDirector(cursor.getString(cursor.getColumnIndex(MovieEntry.DIRECTOR)));
            movie.setYear(cursor.getInt(cursor.getColumnIndex(MovieEntry.YEAR)));
            movie.setPrice(cursor.getDouble(cursor.getColumnIndex(MovieEntry.PRICE)));
            movies.add(movie);
        }
        sqLiteDatabase.close();
        return movies;
    }

    public ArrayList<CD> getCD(){
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String[] columns = {
                CDEntry.ID,
                CDEntry.TITLE,
                CDEntry.GENRE,
                CDEntry.ARTIST,
                CDEntry.YEAR,
                CDEntry.PRICE};
        Cursor cursor = sqLiteDatabase.query(CDEntry.TABLE_NAME, columns,null,
                null,null,null,null);
        ArrayList <CD> cds = new ArrayList<>();

        while (cursor.moveToNext()){
            CD cd = new CD();
            cd.setId(cursor.getString(cursor.getColumnIndex(CDEntry.ID)));
            cd.setTitle(cursor.getString(cursor.getColumnIndex(CDEntry.TITLE)));
            cd.setGenre(cursor.getString(cursor.getColumnIndex(CDEntry.GENRE)));
            cd.setArtist(cursor.getString(cursor.getColumnIndex(CDEntry.ARTIST)));
            cd.setYear(cursor.getInt(cursor.getColumnIndex(CDEntry.YEAR)));
            cd.setPrice(cursor.getDouble(cursor.getColumnIndex(CDEntry.PRICE)));
            cds.add(cd);
        }
        sqLiteDatabase.close();
        return cds;
    }

    public ArrayList<Serie> getSeries(){
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String[] columns = {
                SerieEntry.ID,
                SerieEntry.TITLE,
                SerieEntry.GENRE,
                SerieEntry.DIRECTOR,
                SerieEntry.YEAR,
                SerieEntry.PRICE};
        Cursor cursor = sqLiteDatabase.query(SerieEntry.TABLE_NAME, columns,null,
                null,null,null,null);
        ArrayList <Serie> series = new ArrayList<>();

        while (cursor.moveToNext()){
            Serie serie = new Serie();
            serie.setId(cursor.getString(cursor.getColumnIndex(SerieEntry.ID)));
            serie.setTitle(cursor.getString(cursor.getColumnIndex(SerieEntry.TITLE)));
            serie.setGenre(cursor.getString(cursor.getColumnIndex(SerieEntry.GENRE)));
            serie.setDirector(cursor.getString(cursor.getColumnIndex(SerieEntry.DIRECTOR)));
            serie.setYear(cursor.getInt(cursor.getColumnIndex(SerieEntry.YEAR)));
            serie.setPrice(cursor.getDouble(cursor.getColumnIndex(SerieEntry.PRICE)));
            series.add(serie);
        }
        sqLiteDatabase.close();
        return series;
    }

    public ArrayList<String> getGenresId(){
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String[] columns = {
                GenreEntry.ID
                };
        Cursor cursor = sqLiteDatabase.query(GenreEntry.TABLE_NAME, columns,null,
                null,null,null,null);
        ArrayList <String> genres = new ArrayList<>();

        while (cursor.moveToNext()){
            String id=(cursor.getString(cursor.getColumnIndex(GenreEntry.ID)));
            genres.add(id);
        }
        sqLiteDatabase.close();
        return genres;
    }

    public ArrayList<Artist> getArtists(){
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String[] columns = {
                ArtistEntry.ID,
                ArtistEntry.NAME
        };
        Cursor cursor = sqLiteDatabase.query(ArtistEntry.TABLE_NAME, columns,null,
                null,null,null,null);
        ArrayList <Artist> artists = new ArrayList<>();

        while (cursor.moveToNext()){
            Artist artist = new Artist();
            artist.setId(cursor.getString(cursor.getColumnIndex(ArtistEntry.ID)));
            artists.add(artist);
        }
        sqLiteDatabase.close();
        return artists;
    }

    public ArrayList<Director> getDirectors(){
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String[] columns = {
                DirectorEntry.ID,
                DirectorEntry.NAME
        };
        Cursor cursor = sqLiteDatabase.query(DirectorEntry.TABLE_NAME, columns,null,
                null,null,null,null);
        ArrayList <Director> directors = new ArrayList<>();

        while (cursor.moveToNext()){
            Director director = new Director();
            director.setId(cursor.getString(cursor.getColumnIndex(DirectorEntry.ID)));
            directors.add(director);
        }
        sqLiteDatabase.close();
        return directors;
    }

    public Movie searchById(String id){
        Movie movie = new Movie();
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String[] columns = {
                MovieEntry.ID,
                MovieEntry.TITLE,
                MovieEntry.GENRE,
                MovieEntry.LENGTH,
                MovieEntry.DIRECTOR,
                MovieEntry.YEAR,
                MovieEntry.PRICE };

        String query = "SELECT * FROM " +MovieEntry.TABLE_NAME+ " WHERE id IS " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();
        movie.setId(cursor.getString(cursor.getColumnIndex(MovieEntry.ID)));
        movie.setTitle(cursor.getString(cursor.getColumnIndex(MovieEntry.TITLE)));
        movie.setGenre(cursor.getString(cursor.getColumnIndex(MovieEntry.GENRE)));
        movie.setLength(cursor.getInt(cursor.getColumnIndex(MovieEntry.LENGTH)));
        movie.setDirector(cursor.getString(cursor.getColumnIndex(MovieEntry.DIRECTOR)));
        movie.setYear(cursor.getInt(cursor.getColumnIndex(MovieEntry.YEAR)));
        movie.setPrice(cursor.getDouble(cursor.getColumnIndex(MovieEntry.PRICE)));

        sqLiteDatabase.close();
        return movie;
    }

    public long updateMovie(Movie movie) throws SQLException {

        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieEntry.ID, movie.getId()); //12 digit long
        contentValues.put(MovieEntry.TITLE, movie.getTitle().toString()); //String
        contentValues.put(MovieEntry.GENRE, movie.getGenre().toString()); //String
        contentValues.put(MovieEntry.LENGTH, movie.getLength()); //3 digit number
        contentValues.put(MovieEntry.DIRECTOR, movie.getDirector()); //String
        contentValues.put(MovieEntry.YEAR, movie.getYear());//int
        contentValues.put(MovieEntry.PRICE, movie.getPrice());// 4 digits 2 decimals

        long id= sqLiteDatabase.update(MovieEntry.TABLE_NAME, contentValues,MovieEntry.ID + " = " +movie.getId(), null);
        sqLiteDatabase.close();
        return id;
    }

    public void dropMovie() throws SQLException{
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        sqLiteDatabase.delete(MovieEntry.TABLE_NAME,"",null);
        sqLiteDatabase.delete(ArtistEntry.TABLE_NAME,"",null);
        sqLiteDatabase.delete(CDEntry.TABLE_NAME,"",null);
        sqLiteDatabase.delete(DirectorEntry.TABLE_NAME,"",null);
        sqLiteDatabase.delete(GenreEntry.TABLE_NAME,"",null);
        sqLiteDatabase.delete(SerieEntry.TABLE_NAME,"",null);
        sqLiteDatabase.close();
    }



}
