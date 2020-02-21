package com.example.testing.listener;

import android.app.Activity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.testing.View.viewTemplate;
import com.example.testing.model.Event;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.Movie;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class okEditEventListener implements View.OnClickListener {
    private Model model;
    private Activity activity;
    private viewTemplate editTemplate;
    private Editable title, startDate, endDate, venue, location, movieTitle, movieYear, movieImage;
    private int eventId;
    private int movieId;
    Event event;
    ArrayList<Movie> movies;


    public okEditEventListener(Activity activity, int eventId, int movieId, Editable title, Editable startDate, Editable endDate, Editable venue, Editable location, Editable movieTitle, Editable movieYear, Editable movieImage){
        model = ModelImpl.getSingletonInstance(activity);
        this.activity = activity;
        this.title = title;
        this.eventId = eventId;
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
        //SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z YYYY");
            //model.getItemList();
            movies = model.getItemList();
            Movie mov = movies.get(movieId);
            model.createMovie(eventId, mov);
            String movie = model.getMovieTitle(movieId);
            int year = model.getMovieYear(movieId);
            String image = model.getMovieImage(movieId);
            String startDate = model.getStartDate(eventId);
            String endDate = model.getEndDate(eventId);
           // int movieYearInt = Integer.parseInt(movieYear.toString());
            model.editEvent(eventId, title.toString(), startDate, endDate, venue.toString(), location.toString(), movie, year, image);
            activity.finish();
            Toast.makeText(activity, "Updated", Toast.LENGTH_SHORT).show();


        }









}
