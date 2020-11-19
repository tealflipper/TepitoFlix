package com.example.tepitoflix;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class UpdateMovie extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Movie> movieList;
    private TextInputEditText productId, title, genre,length, director, year, price, artist;
    private Button updateProduct, returnMain;
    MovieDBAdapter movieDBAdapter;
    Media product;
    LinearLayout productLayout;

    Movie updatedMovie;
    String id="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDBAdapter = new MovieDBAdapter(this);
        setContentView(R.layout.activity_update_movie);
        movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("movieList");
        initViewComp();
    }

    public void initViewComp() {
        productId = (TextInputEditText) findViewById(R.id.movieId);
        updateProduct = (Button) findViewById(R.id.updateProduct);
        returnMain =  (Button) findViewById(R.id.returnMain);
        returnMain.setOnClickListener(this);
        updateProduct = (Button) findViewById(R.id.updateProduct);
        updateProduct.setOnClickListener(this);
        productLayout = (LinearLayout) findViewById(R.id.productLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updateProduct:
                String curID = productId.getText().toString();
                if(this.id.equals(curID)){
                    String className =product.getClass().getSimpleName();
                    switch (className){
                        case "Serie":
                            loadSeries();
                            id="";
                            break;
                        case "Movie":
                            loadMovie();
                            id="";
                            break;
                        case "CD":
                            loadCD();
                            id="";
                            break;
                        default:
                            id="";
                            break;
                    }
                }else {
                    id = curID;
                    //search for product with id
                    product = movieDBAdapter.getMediaWithId(this.id);
                    String className =product.getClass().getSimpleName();
                    switch (className){
                        case "Serie":
                            addComponents((Serie) product);
                            break;
                        case "Movie":
                            addComponents((Movie) product);
                            break;
                        case "CD":
                            addComponents((CD) product);
                            break;
                        default:
                            productLayout.removeAllViews();
                            id="";
                            productId.setError("Producto no existe");
                            break;
                    }
                }
                break;

            case R.id.returnMain:
                finish();
                break;
            default:
                break;
        }
    }

    private void addComponents(Movie m){
        productLayout.removeAllViews();
        TextInputLayout titleInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        titleInputLayout.setHint("Titulo Pelicula");
        titleInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        titleInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        title = new TextInputEditText(titleInputLayout.getContext());
        title.setId(R.id.title);
        title.setText(m.getTitle(),TextView.BufferType.EDITABLE);
        title.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        titleInputLayout.addView(title);
        productLayout.addView(titleInputLayout);

        TextInputLayout genreInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        genreInputLayout.setHint("Codigo genero (G0-G16)");
        genreInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        genreInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        genre = new TextInputEditText(genreInputLayout.getContext());
        genre.setId(R.id.genre);
        genre.setText(m.getGenre(),TextView.BufferType.EDITABLE);
        genre.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        genreInputLayout.addView(genre);
        productLayout.addView(genreInputLayout);

        TextInputLayout lengthInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        lengthInputLayout.setHint("Duracion (minutos)");
        lengthInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        lengthInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        length = new TextInputEditText(lengthInputLayout.getContext());
        length.setId(R.id.length);
        length.setText(""+m.getLength(),TextView.BufferType.EDITABLE);
        length.setInputType(InputType.TYPE_CLASS_NUMBER);
        lengthInputLayout.addView(length);
        productLayout.addView(lengthInputLayout);

        TextInputLayout directorInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        directorInputLayout.setHint("Codigo Director (D0-D10)");
        directorInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        directorInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        director = new TextInputEditText(directorInputLayout.getContext());
        director.setId(R.id.director);
        director.setText(m.getDirector(),TextView.BufferType.EDITABLE);
        director.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        directorInputLayout.addView(director);
        productLayout.addView(directorInputLayout);

        TextInputLayout yearInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        yearInputLayout.setHint("Año de estreno");
        yearInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        yearInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        year = new TextInputEditText(yearInputLayout.getContext());
        year.setId(R.id.year);
        year.setText(""+m.getYear(),TextView.BufferType.EDITABLE);
        year.setInputType(InputType.TYPE_CLASS_NUMBER);
        yearInputLayout.addView(year);
        productLayout.addView(yearInputLayout);

        TextInputLayout priceInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        priceInputLayout.setHint("Precio (Pesos)");
        priceInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        priceInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        price = new TextInputEditText(priceInputLayout.getContext());
        price.setId(R.id.price);
        price.setText(""+m.getPrice(),TextView.BufferType.EDITABLE);
        price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        priceInputLayout.addView(price);
        productLayout.addView(priceInputLayout);

    }

    private void addComponents(Serie m){
        productLayout.removeAllViews();
        TextInputLayout titleInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        titleInputLayout.setHint("Titulo Pelicula");
        titleInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        titleInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        title = new TextInputEditText(titleInputLayout.getContext());
        title.setId(R.id.title);
        title.setText(m.getTitle(),TextView.BufferType.EDITABLE);
        title.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        titleInputLayout.addView(title);
        productLayout.addView(titleInputLayout);

        TextInputLayout genreInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        genreInputLayout.setHint("Codigo genero (G0-G16)");
        genreInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        genreInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        genre = new TextInputEditText(genreInputLayout.getContext());
        genre.setId(R.id.genre);
        genre.setText(m.getGenre(),TextView.BufferType.EDITABLE);
        genre.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        genreInputLayout.addView(genre);
        productLayout.addView(genreInputLayout);

        TextInputLayout directorInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        directorInputLayout.setHint("Codigo Director (D0-D10)");
        directorInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        directorInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        director = new TextInputEditText(directorInputLayout.getContext());
        director.setId(R.id.director);
        director.setText(m.getDirector(),TextView.BufferType.EDITABLE);
        director.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        directorInputLayout.addView(director);
        productLayout.addView(directorInputLayout);

        TextInputLayout yearInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        yearInputLayout.setHint("Año de estreno");
        yearInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        yearInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        year = new TextInputEditText(yearInputLayout.getContext());
        year.setId(R.id.year);
        year.setText(""+m.getYear(),TextView.BufferType.EDITABLE);
        year.setInputType(InputType.TYPE_CLASS_NUMBER);
        yearInputLayout.addView(year);
        productLayout.addView(yearInputLayout);

        TextInputLayout priceInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        priceInputLayout.setHint("Precio (Pesos)");
        priceInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        priceInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        price = new TextInputEditText(priceInputLayout.getContext());
        price.setId(R.id.price);
        price.setText(""+m.getPrice(),TextView.BufferType.EDITABLE);
        price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        priceInputLayout.addView(price);
        productLayout.addView(priceInputLayout);

    }

    private void addComponents(CD m){
        productLayout.removeAllViews();
        TextInputLayout titleInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        titleInputLayout.setHint("Titulo Pelicula");
        titleInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        titleInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        title = new TextInputEditText(titleInputLayout.getContext());
        title.setId(R.id.title);
        title.setText(m.getTitle(),TextView.BufferType.EDITABLE);
        title.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        titleInputLayout.addView(title);
        productLayout.addView(titleInputLayout);

        TextInputLayout genreInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        genreInputLayout.setHint("Codigo genero (G0-G16)");
        genreInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        genreInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        genre = new TextInputEditText(genreInputLayout.getContext());
        genre.setId(R.id.genre);
        genre.setText(m.getGenre(),TextView.BufferType.EDITABLE);
        genre.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        genreInputLayout.addView(genre);
        productLayout.addView(genreInputLayout);

        TextInputLayout artistInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        artistInputLayout.setHint("Codigo Artista (D0-D10)");
        artistInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        artistInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        artist = new TextInputEditText(artistInputLayout.getContext());
        artist.setId(R.id.artist);
        artist.setText(m.getArtist(),TextView.BufferType.EDITABLE);
        artist.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        artistInputLayout.addView(artist);
        productLayout.addView(artistInputLayout);

        TextInputLayout yearInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        yearInputLayout.setHint("Año de estreno");
        yearInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        yearInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        year = new TextInputEditText(yearInputLayout.getContext());
        year.setId(R.id.year);
        year.setText(""+m.getYear(),TextView.BufferType.EDITABLE);
        year.setInputType(InputType.TYPE_CLASS_NUMBER);
        yearInputLayout.addView(year);
        productLayout.addView(yearInputLayout);

        TextInputLayout priceInputLayout = new TextInputLayout(this,null,R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
        priceInputLayout.setHint("Precio (Pesos)");
        priceInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        priceInputLayout.setBoxCornerRadii(5, 5, 5, 5);
        price = new TextInputEditText(priceInputLayout.getContext());
        price.setId(R.id.price);
        price.setText(""+m.getPrice(),TextView.BufferType.EDITABLE);
        price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        priceInputLayout.addView(price);
        productLayout.addView(priceInputLayout);

    }

    private void loadMovie(){
        try {
            Movie newMovie = new Movie();
            newMovie.setId((this.productId.getText().toString()));
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
                res = movieDBAdapter.updateMovie(newMovie);
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
                Toast.makeText(this, "Pelicula actualizada", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Codigos genero o director incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadSeries(){
        try {
            Serie serie = new Serie();
            serie.setId((this.productId.getText().toString()));
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
                res = movieDBAdapter.updateSerie(serie);
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
                Toast.makeText(this, "Serie actualizada", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Codigos genero o director incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadCD(){
        try {
            CD cd = new CD();
            cd.setId((this.productId.getText().toString()));
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
                res = movieDBAdapter.updateCD(cd);
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
                Toast.makeText(this, "cd actualizado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Codigos genero o director incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}