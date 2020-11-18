package com.example.tepitoflix;

public class CD extends Media {
    private String artist;

    public CD() {
    }

    public CD(String id, String title, String genre, int year, double price, String artist) {
        super(id, title, genre, year, price);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "\n" + this.getId() +
                "\n \tTITULO: "    + this.getTitle()   + "\n" +
                " \tGENERO: "    + this.getGenre()  + "\n" +
                " \tArtista: "  + this.getArtist()+ "\n" +
                " \tAÑO: "       + this.getYear()    + "\n" +
                " \tPRECIO: $"    + this.getPrice()   + "\n" ;
    }
}
