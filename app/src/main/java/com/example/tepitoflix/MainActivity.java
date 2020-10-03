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

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movieList;
    Button insertMovie,updateMovie,viewMovies,deleteMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //list of movie objects
        movieList = new ArrayList<>();

        insertMovie = (Button) findViewById(R.id.insertMovie);
        updateMovie = (Button) findViewById(R.id.updateMovie);
        viewMovies = (Button) findViewById(R.id.viewMovies);
        deleteMovie = (Button) findViewById(R.id.deleteMovie);

        goToAddMovie();
        goToViewMovieList();
    }

    public void initViewComp() {

    }

    public void goToAddMovie(){

        insertMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ir a visualizar peliculas
                Intent goToActivity = new Intent(v.getContext(), AddMovie.class);
                //Exportar objeto a segunda actividad
                goToActivity.putExtra("movieList", movieList);
                startActivityForResult(goToActivity,1);
            }
        });
    }

    public void goToViewMovieList(){
        viewMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ir a visualizar peliculas
                Intent goToActivity = new Intent(v.getContext(), ViewMovieList.class);
                //Exportar objeto a segunda actividad
                goToActivity.putExtra("movieList", movieList);
                startActivityForResult(goToActivity,2);
            }
        });
    }

    public void goToUpdateMovie(){
        viewMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ir a visualizar peliculas
                Intent goToActivity = new Intent(v.getContext(), UpdateMovie.class);
                //Exportar objeto a segunda actividad
                goToActivity.putExtra("movieList", movieList);
                startActivityForResult(goToActivity,2);
            }
        });
    }

    public void Delete_Movie(){
        viewMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ir a visualizar peliculas
                Intent goToActivity = new Intent(v.getContext(), Delete_Movie.class);
                //Exportar objeto a segunda actividad
                goToActivity.putExtra("movieList", movieList);
                startActivityForResult(goToActivity,2);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 1 || requestCode == 2) && resultCode == RESULT_OK) {
            try {
                this.movieList = (ArrayList<Movie>) data.getSerializableExtra("movieList");
                Toast.makeText(this, "lista" + movieList.size(), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
    }
}