package com.example.tepitoflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //list of movie objects
        movieList = new ArrayList<>();
        goToAddMovie();

    }

    public void goToAddMovie(){
        Button btn = (Button) findViewById(R.id.insertMovie);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ir a visualizar peliculas
                Intent seeMovieList = new Intent(v.getContext(), AddMovie.class);
                //Exportar objeto a segunda actividad
                seeMovieList.putExtra("movieList", movieList);
                startActivityForResult(seeMovieList,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("movieList");
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}