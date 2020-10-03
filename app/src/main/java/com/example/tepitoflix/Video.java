package com.example.tepitoflix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public abstract class Video implements Serializable {
    private String  title;
    private String  genres;
    private int   release;
    private double  length;
    private double  price;

    public Video() {
    }

    public Video(String title, String genres, int release, double length, double price) {
        this.title = title;
        this.genres = genres;
        this.release = release;
        this.length = length;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenre(String genres) {
        this.genres = genres;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double calcIVA(int iva){
        return this.price*iva;
    }
    public double calcISR(int isr){
        return this.price*isr;
    }

}
