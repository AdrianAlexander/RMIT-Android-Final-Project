package com.example.testing.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.testing.Adapter.MovieAdapter;
import com.example.testing.View.EditEvent;
import com.example.testing.View.viewTemplate;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class GotoEditMovieListener implements View.OnClickListener {

    private int edit;
    private Activity context;
    Model model;
    List<Movie> movieList;

    public GotoEditMovieListener(int edit, Activity context){
        this.edit = edit;
        this.context = context;
        model = ModelImpl.getSingletonInstance(context);
        movieList = model.getItemList();
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, viewTemplate.class);
        intent.putExtra("title", movieList.get(edit).getTitle());
        intent.putExtra("year", Integer.toString(movieList.get(edit).getYear()));
        intent.putExtra("position",Integer.toString(edit));
        context.startActivity(intent);
        Toast.makeText(context, "Edit List", Toast.LENGTH_SHORT).show();
    }
}
