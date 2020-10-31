package com.example.tepitoflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ViewMovieList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie_list);
        try {
            printMovieList();
        }catch (JSONException e){
            Toast.makeText(getApplicationContext(), "Error en formato JSON"+e.toString(), Toast.LENGTH_LONG).show();
        }

        returnToMain();

    }



    public void printMovieList() throws JSONException{
        String stringMovies = getIntent().getStringExtra("jsonMovies");
        JSONArray jsonMovies = new JSONArray(stringMovies);
        ArrayList<String> movies = new ArrayList<String>();

        for(int i = 0; i< jsonMovies.length(); i++){
            JSONObject jsonMovie = jsonMovies.getJSONObject(i);
            movies.add(jsonMovie.toString(2));
        }

        ArrayAdapter<String> adapterMovie = new ArrayAdapter<String>(this,
               R.layout.movie_list, movies);
        ListView listViewMovies = (ListView) findViewById(R.id.listview);
        listViewMovies.setAdapter(adapterMovie);
    }



    public void returnToMain(){
        Button btn = (Button) findViewById(R.id.returnMain);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Ir a visualizar peliculas
                    Intent data = new Intent(v.getContext(), MainActivity.class);
                    setResult(RESULT_OK, data);
                    finish();
                }catch (Exception e){
                    Toast.makeText(v.getContext(), "eeeeee", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}