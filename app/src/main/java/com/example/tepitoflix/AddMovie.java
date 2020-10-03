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

public class AddMovie extends AppCompatActivity {
    private ArrayList<Movie> movieList;
    private EditText title, genre, length, director, year, price;
    private TextView movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        //list of movie objects

        movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("movieList");
        initViewComp();
        returnToMain();
        loadMovies();




    }

    public void initViewComp() {
        movieId = (TextView) findViewById(R.id.MovieId);
        title = (EditText) findViewById(R.id.title);
        genre = (EditText) findViewById(R.id.genre);
        length = (EditText) findViewById(R.id.length);
        director = (EditText) findViewById(R.id.director);
        length = (EditText) findViewById(R.id.length);
        year = (EditText) findViewById(R.id.year);
        price = (EditText) findViewById(R.id.price);
        String header = "Pelicula " + movieList.size();
        movieId.setText(header);
    }

    public void loadData(){
        try {
            Movie newMovie = new Movie();
            newMovie.setId(movieList.size());
            newMovie.setTitle(this.title.getText().toString());
            newMovie.setGenre(this.genre.getText().toString());
            newMovie.setDirector(this.director.getText().toString());
            double aux = Double.parseDouble(this.length.getText().toString());
            newMovie.setLength(aux);
            int year= Integer.parseInt(this.year.getText().toString());
            newMovie.setRelease(year);
            aux = Double.parseDouble(this.price.getText().toString());
            newMovie.setPrice(aux);
            //add new movie to list
            movieList.add(newMovie);
            title.setText("");
            genre.setText("");
            length.setText("");
            director.setText("");
            length.setText("");
            this.year.setText("");
            price.setText("");
            String header = "Pelicula " + movieList.size();
            movieId.setText(header);
            Toast.makeText(this, "Pelicula agregada", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void loadMovies(){
        Button btn = (Button) findViewById(R.id.cargar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });




    }

    public void returnToMain(){
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