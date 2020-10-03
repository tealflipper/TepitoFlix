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

    }

    public void initViewComp(){
        temp = (TextView) findViewById(R.id.temp);
    }

    public void printMovieList(ArrayList<Movie> movieList){
        try {
            String out="Lista de Peliculas \n\n";
            for(Movie i : movieList){
                out=out+i.getTitle()+"\n"+
                        i.getGenres()+"\n"+
                        i.getLength()+" min\n"+
                        i.getDirector()+"\n"+
                        i.getRelease() +"\n$"
                        +i.getPrice() + "\n\n";
            }
            temp.setText(out);
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void returnToMain(View v){
        Button btn = (Button) findViewById(R.id.returnMain);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(main,0);
            }
        });
    }
}