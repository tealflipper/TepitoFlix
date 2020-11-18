package com.example.tepitoflix;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.StrictMode;
import android.renderscript.ScriptGroup;
import android.util.JsonReader;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static androidx.core.content.ContextCompat.getSystemService;

public class Tools {
    private static String jsonFile = "https://tepitoflix.000webhostapp.com/movieJson3.json";
    public static String imagesURL = "https://librojson.000webhostapp.com/imagenes/";
    private static String jsonString;
    public Tools(){
    }



    /*
     * Converts JSON array to Arraylist <Movie>
     * */
    public static ArrayList<Movie> jsonArrayToArrayList(JSONArray jsonArray) throws JSONException {
        ArrayList<Movie> movieList = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject curObj= jsonArray.getJSONObject(i);
            Movie movie = new Movie();

            movie.setId(""+(i+1));
            movie.setTitle(curObj.getString("title"));
            movie.setGenre(curObj.getString("genre"));
            movie.setLength(curObj.getInt("length"));
            movie.setDirector(curObj.getString("director"));
            movie.setYear(curObj.getInt("year"));
            movie.setPrice(curObj.getDouble("price"));


            movieList.add(movie);

        }

        return movieList;
    }


    /*
     * Converts ArrayList to String for printing out
     * */
    public static ArrayList<String> ArrayListToStringArray(ArrayList<Movie> movieArrayList){
        ArrayList moviesStringList= new ArrayList<String>();
        int i = 1;
        for (Movie movie : movieArrayList){
            moviesStringList.add(""+(i++)+movie.toString());
        }
        return moviesStringList;
    }

    /*
     * Writes contents of ArrayList to file in JSON format
     * */
    public static void saveMovieJSONFile(File movieFile, ArrayList<Movie> movieList) throws IOException {
        FileWriter fileWriter = new FileWriter(movieFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("[ ");
        for(Movie m:movieList){
            JSONObject jsonMovie = new JSONObject();
            try {
                jsonMovie.put("id", m.getId());
                jsonMovie.put("title", m.getTitle().toString());
                jsonMovie.put("genre", m.getGenre().toString());
                jsonMovie.put("length", m.getLength());
                jsonMovie.put("director", m.getDirector().toString());
                jsonMovie.put("year", m.getYear());
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

    public static void saveSerieJSONFile(File movieFile, ArrayList<Serie> movieList) throws IOException {
        FileWriter fileWriter = new FileWriter(movieFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("[ ");
        for(Serie m:movieList){
            JSONObject jsonMovie = new JSONObject();
            try {
                jsonMovie.put("id", m.getId());
                jsonMovie.put("title", m.getTitle().toString());
                jsonMovie.put("genre", m.getGenre().toString());
                jsonMovie.put("director", m.getDirector().toString());
                jsonMovie.put("year", m.getYear());
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

    public static void saveCDJSONFile(File movieFile, ArrayList<CD> movieList) throws IOException {
        FileWriter fileWriter = new FileWriter(movieFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("[ ");
        for(CD m:movieList){
            JSONObject jsonMovie = new JSONObject();
            try {
                jsonMovie.put("id", m.getId().toString());
                jsonMovie.put("title", m.getTitle().toString());
                jsonMovie.put("genre", m.getGenre().toString());
                jsonMovie.put("director", m.getArtist().toString());
                jsonMovie.put("year", m.getYear());
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

    public static void saveArtistJSONFile(File movieFile, ArrayList<Artist> movieList) throws IOException {
        FileWriter fileWriter = new FileWriter(movieFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("[ ");
        for(Artist m:movieList){
            JSONObject jsonMovie = new JSONObject();
            try {
                jsonMovie.put("id", m.getId().toString());
                jsonMovie.put("name", m.getName().toString());
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

    public static void saveDirectorJSONFile(File movieFile, ArrayList<Director> movieList) throws IOException {
        FileWriter fileWriter = new FileWriter(movieFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("[ ");
        for(Director m:movieList){
            JSONObject jsonMovie = new JSONObject();
            try {
                jsonMovie.put("id", m.getId().toString());
                jsonMovie.put("name", m.getName().toString());
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

    public static void saveGenreJSONFile(File movieFile, ArrayList<Genre> movieList) throws IOException {
        FileWriter fileWriter = new FileWriter(movieFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("[ ");
        for(Genre m:movieList){
            JSONObject jsonMovie = new JSONObject();
            try {
                jsonMovie.put("id", m.getId().toString());
                jsonMovie.put("name", m.getName().toString());
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
    /*
     * Reads JSON format from local file
     * */
    public static JSONArray readJSONFile(File movieFile) throws IOException, JSONException {

        FileReader fileReader = new FileReader(movieFile);
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
        return jsonMovies;
    }

    private static JSONArray readJSONFile(InputStream inputStream) throws IOException, JSONException {
        //creating an InputStreamReader object
        InputStreamReader isReader = new InputStreamReader(inputStream);
        //Creating a BufferedReader object
        BufferedReader reader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
        String str;
        while((str = reader.readLine())!= null){
            sb.append(str);
        }
        System.out.println(sb.toString());
        String stringMovies = sb.toString();
        JSONArray jsonMovies = new JSONArray(stringMovies);
        return jsonMovies;
    }


    public static JSONArray downloadJSONArray(){


        HttpURLConnection conn = null;
        JSONArray jsonArray = new JSONArray();

        try{
            URL url = new URL(jsonFile);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                jsonArray = readJSONFile(conn.getInputStream());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn != null){
                conn.disconnect();
            }
        }
        return jsonArray;
    }
    public static Document downloadXML(String urlString){
        HttpURLConnection conn = null;
        Document doc=null;

        /*
        try{
            URL url = new URL(jsonFile);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.connect();
            int responseCode = conn.getResponseCode();
            int i;
            if (responseCode == HttpURLConnection.HTTP_OK){
                doc = parseXML(conn.getInputStream());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn != null){
                conn.disconnect();
            }
        }
        */
        try {
            InputStream input = new URL(urlString).openStream();
            doc = parseXML(input);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }

    private static  Document parseXML(InputStream inputStream) throws ParserConfigurationException,
            IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        return document;
    }

    public static ArrayList parseItems(Element raiz, String type){
        ArrayList list=null;
        switch (type){
            case "artist":
                list = new ArrayList<Artist>();
                break;
            case "director":
                list = new ArrayList<Director>();
                break;
            case "genre":
                list = new ArrayList<Genre>();
                break;
        }

        NodeList items = raiz.getElementsByTagName(type);
        for (int i = 0; i < items.getLength(); i++){
            Node nodeGenre = items.item(i);
            Item item=null;
            switch (type){
                case "artist":
                    item = new Artist();
                    break;
                case "director":
                    item = new Director();
                    break;
                case "genre":
                    item = new Genre();
                    break;
            }


            for (int j = 0; j < nodeGenre.getChildNodes().getLength(); j++){
                Node curNode = nodeGenre.getChildNodes().item(j);
                if (curNode.getNodeType() == Node.ELEMENT_NODE){
                    if(curNode.getNodeName().equalsIgnoreCase("id")){
                        item.setId(curNode.getChildNodes().item(0).getNodeValue());
                    }
                    else if (curNode.getNodeName().equalsIgnoreCase("name")){
                        item.setName(curNode.getChildNodes().item(0).getNodeValue());
                        int l =0;
                    }
                }
            }
            list.add(item);
        }
        return list;
    }
/*
    public static JSONArray downloadJSONArray(File localFile) throws IOException, JSONException{

        // Get a non-default Storage bucket
        //singleton
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://tepitoflix.appspot.com");

        //create reference to file
        StorageReference storageRef = storage.getReference();
        StorageReference jsonFileRef = storageRef.child("movieJson3.json");

        jsonFileRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Local temp file has been created
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        JSONArray jsonArray = readJSONFile(localFile);
        return jsonArray;
    }
    /*
    public static void uploadJSONFile(File localFile) throws IOException, JSONException{

        // Get a non-default Storage bucket
        //singleton
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://tepitoflix.appspot.com");

        //create reference to file
        StorageReference storageRef = storage.getReference();
        StorageReference jsonFileRef = storageRef.child("movieJson3.json");

        UploadTask uploadTask = jsonFileRef.putFile(Uri.fromFile(localFile));
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }
*/
}
