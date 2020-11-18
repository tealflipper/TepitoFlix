package com.example.tepitoflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
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
        title.setId(R.id.title);
        title.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        titleInputLayout.addView(title);
        productLayout.addView(titleInputLayout);

        TextInputLayout genreInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        genreInputLayout.setHint("Codigo genero (G0-G16)");
        genreInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        genreInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        genre = new TextInputEditText(genreInputLayout.getContext());
        genre.setId(R.id.genre);
        genre.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        genreInputLayout.addView(genre);
        productLayout.addView(genreInputLayout);

        TextInputLayout lengthInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        lengthInputLayout.setHint("Duracion (minutos)");
        lengthInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        lengthInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        length = new TextInputEditText(lengthInputLayout.getContext());
        length.setId(R.id.length);
        length.setInputType(InputType.TYPE_CLASS_NUMBER);
        lengthInputLayout.addView(length);
        productLayout.addView(lengthInputLayout);

        TextInputLayout directorInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        directorInputLayout.setHint("Codigo Director (D0-D10)");
        directorInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        directorInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        director = new TextInputEditText(directorInputLayout.getContext());
        director.setId(R.id.director);
        director.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        directorInputLayout.addView(director);
        productLayout.addView(directorInputLayout);

        TextInputLayout yearInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        yearInputLayout.setHint("Año de estreno");
        yearInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        yearInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        year = new TextInputEditText(yearInputLayout.getContext());
        year.setId(R.id.year);
        year.setInputType(InputType.TYPE_CLASS_NUMBER);
        yearInputLayout.addView(year);
        productLayout.addView(yearInputLayout);

        TextInputLayout priceInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        priceInputLayout.setHint("Precio (Pesos)");
        priceInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        priceInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        price = new TextInputEditText(priceInputLayout.getContext());
        price.setId(R.id.price);
        price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        priceInputLayout.addView(price);
        productLayout.addView(priceInputLayout);

        Button btn = (Button) findViewById(R.id.insertMovie);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bool= valMovie();
                if(bool){
                    loadMovie();
                }

            }
        });
    }

    private boolean valMovie(){
        try {
            String gId = genre.getText().toString();
            String aId = director.getText().toString();
            boolean genreB = movieDBAdapter.valGenreId(gId);
            boolean artistB = movieDBAdapter.valDirectorId(aId);
            if(!genreB){
                genre.setError("genero no existe");
                return false;
            }
            if(!artistB){
                director.setError("director no existe");
                return false;
            }
            return true;
        }catch (Exception e){
            Toast.makeText(this, "Por favor rellana todos los campos", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return false;
    }

    private void loadMovie(){
        try {
            Movie newMovie = new Movie();
            //int id=//movieList.size() > 0 ? movieList.get(movieList.size() - 1).getId()+1 : 0;
            //newMovie.setId((this.id.getText().toString()));
            newMovie.setTitle(title.getText().toString());
            newMovie.setGenre(genre.getText().toString());
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
        TextInputLayout titleInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        titleInputLayout.setHint("Titulo de la serie");
        titleInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        titleInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        title = new TextInputEditText(titleInputLayout.getContext());
        title.setId(R.id.title);
        title.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        titleInputLayout.addView(title);
        productLayout.addView(titleInputLayout);

        TextInputLayout genreInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        genreInputLayout.setHint("Codigo genero (G0-G16)");
        genreInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        genreInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        genre = new TextInputEditText(genreInputLayout.getContext());
        genre.setId(R.id.genre);
        genre.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        genreInputLayout.addView(genre);
        productLayout.addView(genreInputLayout);

        TextInputLayout directorInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        directorInputLayout.setHint("Codigo Director (D0-D10)");
        directorInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        directorInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        director = new TextInputEditText(directorInputLayout.getContext());
        director.setId(R.id.director);
        director.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        directorInputLayout.addView(director);
        productLayout.addView(directorInputLayout);

        TextInputLayout yearInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        yearInputLayout.setHint("Año de estreno");
        yearInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        yearInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        year = new TextInputEditText(yearInputLayout.getContext());
        year.setId(R.id.year);
        year.setInputType(InputType.TYPE_CLASS_NUMBER);
        yearInputLayout.addView(year);
        productLayout.addView(yearInputLayout);

        TextInputLayout priceInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        priceInputLayout.setHint("Precio (Pesos)");
        priceInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        priceInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        price = new TextInputEditText(priceInputLayout.getContext());
        price.setId(R.id.price);
        price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        priceInputLayout.addView(price);
        productLayout.addView(priceInputLayout);

        Button btn = (Button) findViewById(R.id.insertMovie);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valMovie()){
                    loadSeries();
                }

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
                title.setText("");
                genre.setText("");
                director.setText("");
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
        TextInputLayout titleInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        titleInputLayout.setHint("Titulo Pelicula");
        titleInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        titleInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        title = new TextInputEditText(titleInputLayout.getContext());
        title.setId(R.id.title);
        title.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        titleInputLayout.addView(title);
        productLayout.addView(titleInputLayout);

        TextInputLayout genreInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        genreInputLayout.setHint("Codigo genero (G0-G16)");
        genreInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        genreInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        genre = new TextInputEditText(genreInputLayout.getContext());
        genre.setId(R.id.genre);
        genre.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        genreInputLayout.addView(genre);
        productLayout.addView(genreInputLayout);

        TextInputLayout artistInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        artistInputLayout.setHint("Codigo Artista (A0-A17)");
        artistInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        artistInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        artist = new TextInputEditText(artistInputLayout.getContext());
        artist.setId(R.id.artist);
        artist.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        artistInputLayout.addView(artist);
        productLayout.addView(artistInputLayout);

        TextInputLayout yearInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        yearInputLayout.setHint("Año de estreno");
        yearInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        yearInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        year = new TextInputEditText(yearInputLayout.getContext());
        year.setId(R.id.year);
        year.setInputType(InputType.TYPE_CLASS_NUMBER);
        yearInputLayout.addView(year);
        productLayout.addView(yearInputLayout);

        TextInputLayout priceInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        priceInputLayout.setHint("Precio (Pesos)");
        priceInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        priceInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        price = new TextInputEditText(priceInputLayout.getContext());
        price.setId(R.id.price);
        price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        priceInputLayout.addView(price);
        productLayout.addView(priceInputLayout);


        Button btn = (Button) findViewById(R.id.insertMovie);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valCD()){
                    loadCD();
                }

            }
        });
    }

    private boolean valCD(){
        String gId = genre.getText().toString();
        String aId = artist.getText().toString();
        boolean genreB = movieDBAdapter.valGenreId(gId);
        boolean artistB = movieDBAdapter.valArtistId(aId);
        if(!genreB){
            genre.setError("genero no existe");
            return false;
        }
        if(!artistB){
            artist.setError("artista no existe");
            return false;
        }
        return true;
    }



    private void loadCD(){
        try {
            CD cd = new CD();
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