package com.example.testing.model;

import java.util.ArrayList;

public class MovieImpl extends AbstractMovie {

    public MovieImpl(String id, String title, int year, String image){
        super(id, title, year, image);
    }

    public MovieImpl(String title, int year, String image){
        super(title, year, image);
    }

}
