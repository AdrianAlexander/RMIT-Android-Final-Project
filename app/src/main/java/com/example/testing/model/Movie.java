package com.example.testing.model;


import java.util.UUID;

public interface Movie {

    String getId();

    String getTitle();

    void setTitle(String title);

    void setYear(int year);

    void setImage(String image);

    int getYear();

    String getImage();



}
