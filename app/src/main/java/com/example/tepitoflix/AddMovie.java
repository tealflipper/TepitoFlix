package com.example.tepitoflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AddMovie extends AppCompatActivity {
    private ArrayList<Movie> movieList;
    private EditText id,title, genre, length, director, year, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        //list of movie objects

        movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("movieList");
        initViewComp();
        loadMovies();
        returnToMain();
    }

    public void initViewComp() {
        id = (EditText) findViewById(R.id.id);
        title = (EditText) findViewById(R.id.title);
        genre = (EditText) findViewById(R.id.genre);
        length = (EditText) findViewById(R.id.length);
        director = (EditText) findViewById(R.id.director);
        length = (EditText) findViewById(R.id.length);
        year = (EditText) findViewById(R.id.year);
        price = (EditText) findViewById(R.id.price);
    }

    public void loadData(){
        try {
            Movie newMovie = new Movie();
            //int id=//movieList.size() > 0 ? movieList.get(movieList.size() - 1).getId()+1 : 0;
            newMovie.setId(Integer.parseInt(this.id.getText().toString()));
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
            id.setText("");
            title.setText("");
            genre.setText("");
            length.setText("");
            director.setText("");
            length.setText("");
            this.year.setText("");
            price.setText("");
            Toast.makeText(this, "Pelicula agregada", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateId(String id){
        Pattern expRegPat = Pattern.compile("^[0-9]+$");
        if(!expRegPat.matcher(id).matches() || id.length() != 12) {
            this.id.setError("Id invalido (12 digitos)");
            return false;
        }else {
            this.id.setError(null);
        }
        return true;
    }
    private boolean validateTitle(String title){
        Pattern expRegPat = Pattern.compile("^[a-zA-Z0-9 ]+$");
        if(!expRegPat.matcher(title).matches() || title.length() > 45) {
            this.title.setError("Titulo invalido");
            return false;
        }else {
            this.title.setError(null);
        }
        return true;
    }
    private boolean validateGenre(String gen){
        Pattern expRegPat = Pattern.compile("^[a-zA-Z ]+$");
        if(!expRegPat.matcher(gen).matches() || gen.length() > 20) {
            this.genre.setError("Genero invalido");
            return false;
        }else {
            this.genre.setError(null);
        }
        return true;
    }

    private boolean validateLength(String length){
        Pattern expRegPat = Pattern.compile("^[0-9]+$");
        if(!expRegPat.matcher(length).matches() || length.length() > 3) {
            this.length.setError("Duración invalida");
            return false;
        }else {
            this.length.setError(null);
        }
        return true;
    }

    private boolean validateDirector(String dir){
        Pattern expRegPat = Pattern.compile("^[a-zA-Z ]+$");
        if(!expRegPat.matcher(dir).matches() || dir.length() > 20) {
            this.director.setError("Director invalido");
            return false;
        }else {
            this.director.setError(null);
        }
        return true;
    }

    private boolean validateYear(String year){
        Pattern expRegPat = Pattern.compile("^[0-9]+$");
        if(!expRegPat.matcher(year).matches() || year.length() != 4) {
            this.year.setError("Año invalido");
            return false;
        }else {
            this.year.setError(null);
        }
        return true;
    }


    private boolean validatePrice(String price){
        Pattern expRegPat = Pattern.compile("^([0-9]{0,4}((.)[0-9]{0,2}))$");
        if(!expRegPat.matcher(price).matches()) {
            this.price.setError("precio invalido");
            return false;
        }else {
            this.price.setError(null);
        }
        return true;
    }
    private boolean validateMovie(){
        return validateId(this.id.getText().toString())
            && validateTitle(this.title.getText().toString())
            && validateGenre(this.genre.getText().toString())
            && validateLength(this.length.getText().toString())
            && validateDirector(this.director.getText().toString())
            && validateYear(this.year.getText().toString())
            && validatePrice(this.price.getText().toString());
    }

    public void loadMovies(){
        Button btn = (Button) findViewById(R.id.insertMovie);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateMovie()){
                    loadData();
                }

            }
        });
    }

    public void returnToMain(){
        Button btn = (Button) findViewById(R.id.returnMain);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(v.getContext(), MainActivity.class);
                data.putExtra("movieList", movieList);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}