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

public class DeleteMovie extends AppCompatActivity {
    private ArrayList<Movie> movieList;
    private EditText movieId;
    private TextView result;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_movie);
        movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("movieList");
        initViewComp();
        deleteMovieId();
        returnToMain();
    }

    public void initViewComp(){
        movieId = (EditText) findViewById(R.id.movieId);
        result  = (TextView) findViewById(R.id.result);
    }

    public void deleteMovieId(){
        Button btn = (Button) findViewById(R.id.action);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
    }
    public void delete(){
        id=Integer.parseInt(movieId.getText().toString());
        Movie movieToDelete=null;
        for(Movie i: movieList){
            if(id == i.getId()){
                movieToDelete=i;
                break;
            }
        }
        if(movieToDelete!=null){
            movieList.remove(movieToDelete);
            result.setText("Elemento" +id+" eliminado");
        }
        else{
            result.setText("Elemento no encontrado");
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