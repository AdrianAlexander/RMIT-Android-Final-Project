package com.example.testing.listener;

import android.app.Activity;
import android.text.Editable;
import android.view.View;

import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.Movie;

import java.util.ArrayList;

public class okAddEventListener implements View.OnClickListener {

    private Model model;
    private Activity act;
    private Editable id;
    private Editable title;
    private Editable startDate;
    private Editable endDate;
    private Editable venue;
    private Editable location;
    private Editable movieTitle;
    private Editable movieYear;
    private Editable movieImage;
    private int movieId;
    ArrayList<Movie> movies;

    public okAddEventListener(Activity act, int movieId, Editable id, Editable title, Editable startDate, Editable endDate, Editable venue, Editable location, Editable movieTitle, Editable movieYear, Editable movieImage){
        model = ModelImpl.getSingletonInstance(act);
        this.act = act;
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.venue = venue;
        this.location = location;
        this.movieTitle = movieTitle;
        this.movieYear = movieYear;
        this.movieId = movieId;
        this.movieImage = movieImage;

    }
    @Override
    public void onClick(View v) {
        movies = model.getItemList();
        Movie mov = movies.get(movieId);
        //model.createMovieAdd(mov);
        String movie = model.getMovieTitle(movieId);
        int year = model.getMovieYear(movieId);
        String image = model.getMovieImage(movieId);
        String addStartDate = model.addGetStartDate();
        String addEndDate = model.addGetEndDate();
        model.addEvent(id.toString(), title.toString(), addStartDate, addEndDate, venue.toString(), location.toString(), movie, year, mov, image);
        act.finish();
    }
}
