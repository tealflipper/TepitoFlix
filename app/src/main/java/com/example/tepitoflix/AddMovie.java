package com.example.tepitoflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddMovie extends AppCompatActivity implements View.OnClickListener{

    private RadioGroup productGroup;
    private RadioButton movieRadio, serieRadio, cdRadio;
    private ArrayList<Movie> movieList;
    private Button returnMain,insertMovie;
    private TextInputEditText id, title, genre,length, director, year, price, artist;
    MovieDBAdapter movieDBAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        //list of movie objects
        movieDBAdapter = new MovieDBAdapter(this);
        movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("movieList");
        initViewComp();
        returnToMain();


// fill in any details dynamically here

// insert into main view

    }

    public void initViewComp() {
        productGroup = findViewById(R.id.radioGroup);
        movieRadio = findViewById(R.id.movieRadio);
        movieRadio.setOnClickListener(this);
        serieRadio = findViewById(R.id.serieRadio);
        serieRadio.setOnClickListener(this);
        cdRadio = findViewById(R.id.cdRadio);
        cdRadio.setOnClickListener(this);

        returnMain = findViewById(R.id.returnMain);
        returnMain.setOnClickListener(this);
        insertMovie = findViewById(R.id.insertMovie);
        insertMovie.setOnClickListener(this);


        /*
        id = (EditText) findViewById(R.id.id);
        title = (EditText) findViewById(R.id.title);
        genre = (EditText) findViewById(R.id.genre);
        length = (EditText) findViewById(R.id.length);
        director = (EditText) findViewById(R.id.director);
        year = (EditText) findViewById(R.id.year);
        price = (EditText) findViewById(R.id.price);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.movieRadio:
                cdRadio.setChecked(false);
                serieRadio.setChecked(false);
                addMovie();
                break;

            case R.id.serieRadio:
                movieRadio.setChecked(false);
                cdRadio.setChecked(false);
                addSeries();
                break;

            case R.id.cdRadio:
                movieRadio.setChecked(false);
                serieRadio.setChecked(false);
                addCD();
                break;
            case R.id.returnMain:
                Intent data = new Intent(v.getContext(), MainActivity.class);
                finish();
                break;
            default:
                break;
        }
    }

    private void addMovie(){
        LinearLayout productLayout = findViewById(R.id.productLayout);
        productLayout.removeAllViews();
        TextInputLayout titleInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        titleInputLayout.setHint("Titulo Pelicula");
        titleInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        titleInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        title = new TextInputEditText(titleInputLayout.getContext());
        titleInputLayout.addView(title);
        productLayout.addView(titleInputLayout);
        genre = new EditText(this);
        genre.setHint("Codigo genero (G0-G16)");
        genre.setId(R.id.genre);
        genre.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(genre);
        length = new EditText(this);
        length.setHint("Duración (minutos)");
        length.setId(R.id.length);
        length.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(length);
        director = new EditText(this);
        director.setHint("Codigo director (D0-D10)");
        director.setId(R.id.director);
        director.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(director);
        year = new EditText(this);
        year.setHint("Año de estreno");
        year.setId(R.id.year);
        year.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(year);
        price = new EditText(this);
        price.setHint("precio pelicula");
        price.setId(R.id.price);
        price.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(price);

        Button btn = (Button) findViewById(R.id.insertMovie);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMovie();

            }
        });
    }
    private void loadMovie(){
        try {
            Movie newMovie = new Movie();
            //int id=//movieList.size() > 0 ? movieList.get(movieList.size() - 1).getId()+1 : 0;
            //newMovie.setId((this.id.getText().toString()));
            newMovie.setTitle(this.title.getText().toString());
            newMovie.setGenre(this.genre.getText().toString());
            newMovie.setDirector(this.director.getText().toString());
            int aux = Integer.parseInt(this.length.getText().toString());
            newMovie.setLength(aux);
            int year= Integer.parseInt(this.year.getText().toString());
            newMovie.setYear(year);
            double dd = Double.parseDouble(this.price.getText().toString());
            newMovie.setPrice(dd);
            //add new movie to list
            long res=0;
            try {
                res = movieDBAdapter.insertMovie(newMovie);
            } catch (SQLException e) {
                Toast.makeText(this, "Codigos genero o director incorrectos", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            if(res>0){
                id.setText("");
                title.setText("");
                genre.setText("");
                length.setText("");
                director.setText("");
                length.setText("");
                this.year.setText("");
                price.setText("");
                Toast.makeText(this, "Pelicula agregada", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Codigos genero o director incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void addSeries(){
        LinearLayout productLayout = findViewById(R.id.productLayout);
        productLayout.removeAllViews();

        id = new EditText(this);
        id.setHint("ID Serie");
        id.setId(R.id.id);
        id.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        title = new EditText(this);
        title.setHint("Titulo serie");
        title.setId(R.id.title);
        title.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(title);
        genre = new EditText(this);
        genre.setHint("Codigo genero (G0-G16)");
        genre.setId(R.id.genre);
        genre.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(genre);
        director = new EditText(this);
        director.setHint("Codigo director (D0-D10)");
        director.setId(R.id.director);
        director.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(director);
        year = new EditText(this);
        year.setHint("Año de estreno");
        year.setId(R.id.year);
        year.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(year);
        price = new EditText(this);
        price.setHint("precio Serie");
        price.setId(R.id.price);
        price.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(price);

        Button btn = (Button) findViewById(R.id.insertMovie);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSeries();

            }
        });
    }

    private void loadSeries(){
        try {
            Serie serie = new Serie();
            //int id=//movieList.size() > 0 ? movieList.get(movieList.size() - 1).getId()+1 : 0;
            //serie.setId((this.id.getText().toString()));
            serie.setTitle(this.title.getText().toString());
            serie.setGenre(this.genre.getText().toString());
            serie.setDirector(this.director.getText().toString());
            int year= Integer.parseInt(this.year.getText().toString());
            serie.setYear(year);
            double dd = Double.parseDouble(this.price.getText().toString());
            serie.setPrice(dd);
            //add new movie to list
            long res=0;
            try {
                res = movieDBAdapter.insertSerie(serie);
            } catch (SQLException e) {
                Toast.makeText(this, "Codigos genero o director incorrectos", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            if(res>0){
                id.setText("");
                title.setText("");
                genre.setText("");
                director.setText("");
                length.setText("");
                this.year.setText("");
                price.setText("");
                Toast.makeText(this, "Serie agregada", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Codigos genero o director incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    private void addCD(){
        LinearLayout productLayout = findViewById(R.id.productLayout);
        productLayout.removeAllViews();

        TextInputLayout textInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        textInputLayout.setHint("Titulo CD");
        textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        textInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        title = new TextInputEditText(textInputLayout.getContext());
        textInputLayout.addView(title);
        productLayout.addView(textInputLayout);

        TextInputLayout genreInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        textInputLayout.setHint("Titulo CD");
        textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        textInputLayout.setBoxCornerRadii(5, 5, 5, 5);

        genre = new EditText(this);
        genre.setHint("Codigo genero (G0-G16)");
        genre.setId(R.id.genre);
        genre.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(genre);
        artist = new EditText(this);
        artist.setHint("Codigo artista (A0-A17)");
        artist.setId(R.id.artist);
        artist.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(artist);
        year = new EditText(this);
        year.setHint("Año de estreno");
        year.setId(R.id.year);
        year.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(year);
        price = new EditText(this);
        price.setHint("precio CD");
        price.setId(R.id.price);
        price.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        productLayout.addView(price);

        Button btn = (Button) findViewById(R.id.insertMovie);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCD();

            }
        });
    }

    private void loadCD(){
        try {
            CD cd = new CD();
            //int id=//movieList.size() > 0 ? movieList.get(movieList.size() - 1).getId()+1 : 0;
            cd.setId((this.id.getText().toString()));
            cd.setTitle(this.title.getText().toString());
            cd.setGenre(this.genre.getText().toString());
            cd.setArtist(this.artist.getText().toString());
            int year= Integer.parseInt(this.year.getText().toString());
            cd.setYear(year);
            double dd = Double.parseDouble(this.price.getText().toString());
            cd.setPrice(dd);
            //add new movie to list
            long res=0;
            try {
                res = movieDBAdapter.insertCD(cd);
            } catch (SQLException e) {
                Toast.makeText(this, "Codigos genero o director incorrectos", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            if(res>0){
                title.setText("");
                genre.setText("");
                artist.setText("");
                this.year.setText("");
                price.setText("");
                Toast.makeText(this, "cd agregado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Codigos genero o director incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
/*
    public void loadData(){
        try {
            Movie newMovie = new Movie();
            //int id=//movieList.size() > 0 ? movieList.get(movieList.size() - 1).getId()+1 : 0;
            newMovie.setId((this.id.getText().toString()));
            newMovie.setTitle(this.title.getText().toString());
            newMovie.setGenre(this.genre.getText().toString());
            newMovie.setDirector(this.director.getText().toString());
            int aux = Integer.parseInt(this.length.getText().toString());
            newMovie.setLength(aux);
            int year= Integer.parseInt(this.year.getText().toString());
            newMovie.setYear(year);
            double dd = Double.parseDouble(this.price.getText().toString());
            newMovie.setPrice(dd);
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
            this.title.setError("Id ya existe");
        }
        return true;
    }
    private boolean validateTitle(String title){
        Pattern expRegPat = Pattern.compile("^[a-zA-Z0-9ñáéíóúüÁÉÍÓÚÜ ]+$");
        if(!expRegPat.matcher(title).matches() || title.length() > 45) {
            this.title.setError("Titulo invalido");
            return false;
        }else {
            this.title.setError(null);
        }

        //check if title exist
        /*TODO check in database
        int idLocal = Integer.parseInt(this.id.getText().toString());
        //busca el id
        for(Movie i : movieList){
            if(idLocal == i.getId()){
                return false;
            }
        }

        return true;
    }
    private boolean validateGenre(String gen){
        Pattern expRegPat = Pattern.compile("^[a-zA-ZñáéíóúüÁÉÍÓÚÜ ]+$");
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
*/
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