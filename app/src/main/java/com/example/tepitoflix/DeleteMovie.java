package com.example.tepitoflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class DeleteMovie extends AppCompatActivity {
    private ArrayList<Movie> movieList;
    private TextInputEditText movieId;
    String id;
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
        movieId = (TextInputEditText) findViewById(R.id.movieId);

    }

    public void deleteMovieId(){
        Button btn = (Button) findViewById(R.id.action);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=movieId.getText().toString();
                MovieDBAdapter movieDBAdapter =new MovieDBAdapter(DeleteMovie.this);
                int res = movieDBAdapter.deleteById(id);
                if(res == 0){
                    Toast.makeText(DeleteMovie.this, "Producto no existe",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DeleteMovie.this, "Producto eliminado",
                            Toast.LENGTH_SHORT).show();
                }
                movieId.setText("");
            }
        });
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