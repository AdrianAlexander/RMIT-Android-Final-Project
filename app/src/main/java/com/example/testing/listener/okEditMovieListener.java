package com.example.testing.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.testing.View.MovieActivity;
import com.example.testing.View.viewTemplate;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;

public class okEditMovieListener implements View.OnClickListener {
    private Model model;
    private Activity context;
    private viewTemplate editTemplate;
    private Editable title, year;
    private int movieId;

    public okEditMovieListener(Activity context, int movieId, Editable title, Editable year) {
        this.context = context;
        model = ModelImpl.getSingletonInstance(context);
        this.movieId = movieId;
        this.title = title;
        this.year = year;
    }
    @Override
    public void onClick(View v) {
        //Intent intent = new Intent(context, MovieActivity.class);
        //intent.putExtra("editTitle", editTemplate.movieTitle.toString() );
        //intent.putExtra("editYear", editTemplate.movieYear.toString());
        //intent.putExtra()
        int integerYear  = Integer.parseInt(year.toString());
        model.editMovie(movieId, title.toString(), integerYear);
        Log.i("UPDATE", "onCreate: " + integerYear + title.toString());

        context.finish();
    }
}
