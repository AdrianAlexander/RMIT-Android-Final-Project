package com.example.testing.model;

import java.util.UUID;

public class AbstractMovie implements Movie{

    UUID uuid = UUID.randomUUID();
    private String id ;
    private String title;
    public String image;
    private int year;

    public AbstractMovie(){

    }

    public AbstractMovie(String id, String title, int year, String image){
        this.id = id;
        this.title = title;
        this.year = year;
        this.image = image;
    }

    public AbstractMovie(String title, int year,String image){
        this.title = title;
        this.year = year;
        this.image = image;
    }

    @Override
    public String getId() {
        return id;
    }


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return String.format("Id = %s, title = %s, year = %s, image = %s", id, title, year, image);
    }
}
