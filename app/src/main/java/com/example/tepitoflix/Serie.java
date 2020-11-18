package com.example.tepitoflix;

public class Serie extends Media{
    private String director;

    public Serie(String id, String title, String genre, int year, double price, String director) {
        super(id, title, genre, year, price);
        this.director = director;
    }

    public Serie() {

    }


    public String getDirector() {
            return director;
        }

    public void setDirector(String director) {
            this.director = director;
        }

    @Override
    public String toString(){
        return "\n\tID= " + this.getId() + "\n" +
                "\n \tTITULO: "    + this.getTitle()   + "\n" +
                " \tGENERO: "    + this.getGenre()  + "\n" +
                " \tDIRECTOR: "  + this.getDirector()+ "\n" +
                " \tAÑO: "       + this.getYear()    + "\n" +
                " \tPRECIO: "    + this.getPrice()   + "\n" ;
    }
}


