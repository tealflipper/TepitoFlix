package com.example.tepitoflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String genresURL = "https://tepitoflix.000webhostapp.com/Genres.xml";
    String artistURL = "https://tepitoflix.000webhostapp.com/Artists.xml";
    String directorsURL = "https://tepitoflix.000webhostapp.com/Directors.xml";
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    MovieDBAdapter movieDBAdapter;

    private ArrayList<Movie> movieList;
    Button insertMovie,viewMovies, deleteMovie, updateMovie;
    JSONArray jsonMovies;

    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
        setContentView(R.layout.activity_main);

        //list of movie objects
        movieList = new ArrayList<>();
        movieDBAdapter = new MovieDBAdapter(this);
        initViewComp();

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            //download file
            Toast.makeText(this, "Conexion ok", Toast.LENGTH_SHORT).show();
            //get file to list of movies
            downloadFiles();
            Toast.makeText(this, "Archivos descargados", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error de conexion", Toast.LENGTH_SHORT).show();;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //create files

        File fileM = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "Movie.json");
        File fileS = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "Serie.json");
        File fileC = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "CD.json");
        File fileA = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "Artist.json");
        File fileG = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "Genre.json");
        File fileD = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "Director.json");
        File file = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "Database.txt");

        //get lists from database
        ArrayList<Movie> movies = movieDBAdapter.getMovies();
        ArrayList<Serie> series = movieDBAdapter.getSeries();
        ArrayList<CD> cds = movieDBAdapter.getCD();
        ArrayList<Artist> artists = movieDBAdapter.getArtists();
        ArrayList<Genre> genres = movieDBAdapter.getGenres();
        ArrayList<Director> directors = movieDBAdapter.getDirectors();

        //save lists to files
        try {
            Tools.saveToMoviesJSONFile(fileM,movies);
            Tools.saveSerieJSONFile(fileS,series);
            Tools.saveCDJSONFile(fileC,cds);
            Tools.saveArtistJSONFile(fileA,artists);
            Tools.saveGenreJSONFile(fileG,genres);
            Tools.saveDirectorJSONFile(fileD,directors);
            Tools.saveAll(file,genres,directors,artists,cds,series,movies);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Archivos DB guardos", Toast.LENGTH_SHORT).show();
    }



    public void initViewComp() {
        insertMovie = (Button) findViewById(R.id.insertMovie);
        insertMovie.setOnClickListener(this);
        viewMovies  = (Button) findViewById(R.id.viewMovies);
        viewMovies.setOnClickListener(this);
        deleteMovie = (Button) findViewById(R.id.deleteMovie);
        deleteMovie.setOnClickListener(this);
        updateMovie = (Button) findViewById(R.id.updateMovie);
        updateMovie.setOnClickListener(this);
    }


    @Override
    public void onClick(View v){
        Intent goToActivity=null;
        switch (v.getId()) {
            case R.id.insertMovie:
                goToActivity = new Intent(v.getContext(), AddMovie.class);
                startActivityForResult(goToActivity,1);
                break;

            case R.id.viewMovies:
                goToActivity= new Intent(v.getContext(), ViewMovieList.class);
                startActivityForResult(goToActivity,1);
                break;

            case R.id.deleteMovie:
                goToActivity = new Intent(v.getContext(), DeleteMovie.class);
                startActivityForResult(goToActivity,1);
                break;

            case R.id.updateMovie:
                //guardar archivo json de peliculas
                goToActivity = new Intent(v.getContext(), UpdateMovie.class);
                startActivityForResult(goToActivity,1);
                break;

            default:
                break;
        }
    }
    public void downloadFiles(){

        //download files and create databases
        Document document = Tools.downloadXML(genresURL);
        ArrayList<Genre> genres = Tools.parseItems(document.getDocumentElement(),"genre");
        for(Genre genre : genres){
            try {
                movieDBAdapter.insertGenre(genre);
            }catch (SQLException e){
                Log.d(TAG, "ya existe registro");
            }

        }
        document = Tools.downloadXML(artistURL);
        ArrayList<Artist> artists = Tools.parseItems(document.getDocumentElement(),"artist");
        for (Artist artist : artists){
            try {
                movieDBAdapter.insertArtist(artist);
            } catch (SQLException e) {
                Log.d(TAG, "ya existe registro");
            }
        }
        document = Tools.downloadXML(directorsURL);
        ArrayList<Director> directors = Tools.parseItems(document.getDocumentElement(),"director");
        for (Director director : directors){
            try {
                movieDBAdapter.insertDirector(director);
            } catch (SQLException e) {
                Log.d(TAG, "ya existe registro");
            }

        }
    }



}

