package com.example.tepitoflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ViewMovieList extends AppCompatActivity implements View.OnClickListener {
    private MovieDBAdapter movieDBAdapter;
    private RadioButton movieRadio, serieRadio, cdRadio;
    private Button returnMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDBAdapter = new MovieDBAdapter(this);
        setContentView(R.layout.activity_view_movie_list);
        initViewComp();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.movieRadio:
                cdRadio.setChecked(false);
                serieRadio.setChecked(false);
                printMovieList();
                break;

            case R.id.serieRadio:
                movieRadio.setChecked(false);
                cdRadio.setChecked(false);
                printSerieList();
                break;

            case R.id.cdRadio:
                movieRadio.setChecked(false);
                serieRadio.setChecked(false);
                printCDList();
                break;
            case R.id.returnMain:
                finish();
                break;
            default:
                break;
        }
    }

    public void initViewComp() {
        movieRadio = findViewById(R.id.movieRadio);
        movieRadio.setOnClickListener(this);
        serieRadio = findViewById(R.id.serieRadio);
        serieRadio.setOnClickListener(this);
        cdRadio = findViewById(R.id.cdRadio);
        cdRadio.setOnClickListener(this);

        returnMain = findViewById(R.id.returnMain);
        returnMain.setOnClickListener(this);
    }


    public void printMovieList(){
        ArrayList<Movie> movieArrayList = movieDBAdapter.getMovies();
        ArrayList<String> movies = new ArrayList<>();
        for (Movie movie: movieArrayList){
            movies.add(movie.toString());
        }

        ArrayAdapter<String> adapterMovie = new ArrayAdapter<String>(this,
               R.layout.movie_list, movies);
        ListView listViewMovies = (ListView) findViewById(R.id.listview);
        listViewMovies.setAdapter(adapterMovie);
    }

    public void printSerieList(){
        ArrayList<Serie> movieArrayList = movieDBAdapter.getSeries();
        ArrayList<String> strings = new ArrayList<>();
        for (Serie serie: movieArrayList){
            strings.add(serie.toString());
        }

        ArrayAdapter<String> adapterMovie = new ArrayAdapter<String>(this,
                R.layout.movie_list, strings);
        ListView listViewMovies = (ListView) findViewById(R.id.listview);
        listViewMovies.setAdapter(adapterMovie);
    }

    public void printCDList(){
        ArrayList<CD> movieArrayList = movieDBAdapter.getCD();
        ArrayList<String> strings = new ArrayList<>();
        for (CD cd: movieArrayList){
            strings.add(cd.toString());
        }

        ArrayAdapter<String> adapterMovie = new ArrayAdapter<String>(this,
                R.layout.movie_list, strings);
        ListView listViewMovies = (ListView) findViewById(R.id.listview);
        listViewMovies.setAdapter(adapterMovie);
    }

}