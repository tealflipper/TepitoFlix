package com.example.tepitoflix;

import java.io.Serializable;

public class Media implements Serializable {
    private String id;
    private String  title;
    private String  genre;
    private int     year;
    private double  price;

    public Media() {
    }

    public Media(String id, String title, String genre, int year, double price) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genres) {
        this.genre = genres;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return null;
    }
}
