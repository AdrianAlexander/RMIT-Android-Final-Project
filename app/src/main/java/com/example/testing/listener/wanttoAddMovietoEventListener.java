package com.example.testing.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.testing.View.EditEvent;
import com.example.testing.View.MainActivity;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.Movie;

import java.util.List;

public class wanttoAddMovietoEventListener implements View.OnClickListener {

    private int position;
    private Activity context;
    private String id;
    private int year;
    Model model;
    List<Movie> movies;

    public wanttoAddMovietoEventListener(Activity context, int position, String id, int year){
        model = ModelImpl.getSingletonInstance(context);
        this.context = context;
        this.position = position;
        this.id = id;
        this.year = year;
        movies = model.getItemList();
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, EditEvent.class);
        intent.putExtra("title", movies.get(position).getTitle() );
        intent.putExtra("year", movies.get(position).getYear());


    }
}
