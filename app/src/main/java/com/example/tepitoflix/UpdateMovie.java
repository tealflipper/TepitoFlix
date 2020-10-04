package com.example.tepitoflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateMovie extends AppCompatActivity {
    private ArrayList<Movie> movieList;
    private EditText title, genre, length, director, year, price, movieId;
    Movie updatedMovie;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);
        movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("movieList");
        initViewComp();
        lookupMovieId();
        upDateMovieId();
        returnToMain();
    }

    public void initViewComp() {
        movieId = (EditText) findViewById(R.id.movieId);
        title = (EditText) findViewById(R.id.title);
        genre = (EditText) findViewById(R.id.genre);
        length = (EditText) findViewById(R.id.length);
        director = (EditText) findViewById(R.id.director);
        length = (EditText) findViewById(R.id.length);
        year = (EditText) findViewById(R.id.year);
        price = (EditText) findViewById(R.id.price);
    }

    public void lookupMovieId() {
        Button btn = (Button) findViewById(R.id.search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAttributes();
            }
        });
    }

    public void upDateMovieId(){
        Button btn = (Button) findViewById(R.id.updateMovie);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAttributes();
            }
        });
    }

    public void setAttributes(){
        try {
            //busca el id
            for(Movie i : movieList){
                if(this.id == i.getId()){
                    updatedMovie=i;
                }
            }
            updatedMovie.setTitle(this.title.getText().toString());
            updatedMovie.setGenre(this.genre.getText().toString());
            updatedMovie.setDirector(this.director.getText().toString());
            double aux = Double.parseDouble(this.length.getText().toString());
            updatedMovie.setLength(aux);
            int year = Integer.parseInt(this.year.getText().toString());
            updatedMovie.setRelease(year);
            aux = Double.parseDouble(this.price.getText().toString());
            updatedMovie.setPrice(aux);
            movieList.set(id,updatedMovie);
            Toast.makeText(this, "Pelicula actualizada", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void getAttributes(){
        try {
            id=Integer.parseInt(movieId.getText().toString());
            //busca el id
            for(Movie i : movieList){
                if(this.id == i.getId()){
                    updatedMovie=i;
                }
            }
            //show movie atributes
            title.setText(updatedMovie.getTitle().toString(), TextView.BufferType.EDITABLE);
            genre.setText(updatedMovie.getGenres().toString(),TextView.BufferType.EDITABLE);
            length.setText(""+updatedMovie.getLength(), TextView.BufferType.EDITABLE);
            director.setText(updatedMovie.getDirector().toString(), TextView.BufferType.EDITABLE);
            length.setText(""+updatedMovie.getLength(), TextView.BufferType.EDITABLE);
            year.setText(""+updatedMovie.getRelease(), TextView.BufferType.EDITABLE);
            price.setText(""+updatedMovie.getPrice(), TextView.BufferType.EDITABLE);
        }catch (Exception e){
            Toast.makeText(this, "Pelicula no encontrada", Toast.LENGTH_SHORT).show();
        }

    }
    public void returnToMain() {
        Button btn = (Button) findViewById(R.id.returnMain);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ir a visualizar peliculas
                Intent data = new Intent(v.getContext(), MainActivity.class);
                //Exportar objeto a segunda actividad
                data.putExtra("movieList", movieList);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}