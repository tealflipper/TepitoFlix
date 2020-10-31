package com.example.tepitoflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Movie> movieList;
    Button insertMovie,viewMovies,saveMovie;
    JSONArray jsonMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //list of movie objects
        movieList = new ArrayList<>();

        initViewComp();
    }

    public void initViewComp() {
        insertMovie = (Button) findViewById(R.id.insertMovie);
        insertMovie.setOnClickListener(this);
        viewMovies  = (Button) findViewById(R.id.viewMovies);
        viewMovies.setOnClickListener(this);
        saveMovie   = (Button) findViewById(R.id.saveMovies);
        saveMovie.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent goToActivity=null;
        switch (v.getId()) {
            case R.id.insertMovie:
                //Ir a visualizar peliculas
                goToActivity = new Intent(v.getContext(), AddMovie.class);
                //Exportar objeto a segunda actividad
                goToActivity.putExtra("movieList", movieList);
                startActivityForResult(goToActivity,1);
                break;

            case R.id.viewMovies:
                try {
                    this.jsonMovies = readJSONFile();
                    goToActivity= new Intent(v.getContext(), ViewMovieList.class);
                    goToActivity.putExtra("jsonMovies", this.jsonMovies.toString());
                    startActivityForResult(goToActivity,2);
                    Toast.makeText(getApplicationContext(), "Archivo leido", Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Error leyendo el archivo"+e.toString(), Toast.LENGTH_SHORT).show();
                }catch (JSONException e){
                    Toast.makeText(getApplicationContext(), "Error en formato JSON"+e.toString(), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.saveMovies:
                //guardar archivo json de peliculas
                try {
                    saveJSONFile();
                    Toast.makeText(this, "Archivo guardado", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error escribiendo archivo", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    private void saveJSONFile() throws IOException {
        //usa el arraylist movielist

        File movieFile = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "movies.json");
        FileWriter fileWriter = new FileWriter(movieFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("[ ");
        for(Movie m:movieList){
            JSONObject jsonMovie = new JSONObject();
            try {
                jsonMovie.put("id", m.getId());
                jsonMovie.put("title", m.getTitle().toString());
                jsonMovie.put("genre", m.getGenres().toString());
                jsonMovie.put("length", m.getLength());
                jsonMovie.put("director", m.getDirector().toString());
                jsonMovie.put("year", m.getRelease());
                jsonMovie.put("price", m.getPrice());
            } catch (JSONException e){
                e.printStackTrace();
            }
            String stringMovie = jsonMovie.toString();
            bufferedWriter.write(stringMovie);
            if (m.getId()!= movieList.get(movieList.size()-1).getId()){
                bufferedWriter.write(",");
            }

        }
        bufferedWriter.write("]");
        bufferedWriter.close();

    }

    private JSONArray readJSONFile() throws IOException, JSONException {
        File file = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "movies.json");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while(line != null){
            stringBuilder.append(line).append("\n");
            line =bufferedReader.readLine();
        }
        bufferedReader.close();

        String stringMovies = stringBuilder.toString();

        JSONArray jsonMovies = new JSONArray(stringMovies);
        //Toast.makeText(getApplicationContext(), jsonMovies.toString(), Toast.LENGTH_LONG).show();
        return jsonMovies;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 1
                || requestCode == 3|| requestCode == 4) && resultCode == RESULT_OK) {
            try {
                this.movieList = (ArrayList<Movie>) data.getSerializableExtra("movieList");
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if (requestCode == 2){
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
    }
}