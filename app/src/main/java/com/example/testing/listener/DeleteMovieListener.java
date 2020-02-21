package com.example.testing.listener;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.testing.Adapter.MovieAdapter;
import com.example.testing.R;
import com.example.testing.View.MovieActivity;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.Movie;

import java.util.ArrayList;

public class DeleteMovieListener implements View.OnClickListener {

    private Activity act;
    private int movieId;
    Model model;

    public DeleteMovieListener(Activity act, int movieId){
        this.act = act;
        this.movieId = movieId;
        model = ModelImpl.getSingletonInstance(act);

    }
    @Override
    public void onClick(View v) {
        model.deleteMovie(movieId);


    }
}
