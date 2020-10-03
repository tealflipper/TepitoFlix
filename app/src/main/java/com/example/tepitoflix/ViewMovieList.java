package com.example.tepitoflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewMovieList extends AppCompatActivity {
    private TextView temp;
    private ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie_list);
        initViewComp();
        movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("movieList");
        printMovieList(movieList);
        returnToMain();

    }

    public void initViewComp(){
        temp = (TextView) findViewById(R.id.temp);
    }

    public void printMovieList(ArrayList<Movie> movieList){
        try {
            String out="Lista de Peliculas \n\n";
            for(Movie i : movieList){
                out=out+"id: " + i.getId()+
                        "Titulo: "+ i.getTitle()+"\n"+
                        "Genero: "+i.getGenres()+"\n"+
                        "Duración: "+i.getLength()+" min\n"+
                        "Director: "+i.getDirector()+"\n"+
                        "Estreno: "+i.getRelease() +"\n"+
                        "Precio: $"+i.getPrice() + "\n\n";
            }
            temp.setText(out);
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void returnToMain(){
        Button btn = (Button) findViewById(R.id.returnMain);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Ir a visualizar peliculas
                    Intent data = new Intent(v.getContext(), MainActivity.class);
                    //Exportar objeto a segunda actividad
                    data.putExtra("movieList", movieList);
                    setResult(RESULT_OK, data);
                    finish();
                }catch (Exception e){
                    Toast.makeText(v.getContext(), "eeeeee", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}