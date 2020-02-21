package com.example.testing.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.testing.View.MovieActivity;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;

import static android.app.Activity.RESULT_OK;

public class GotoAddMovieForEvent implements View.OnClickListener{

    Activity context;
   // private Model model;
   // int position;
    static int REQUEST_CODE = 0;

    public GotoAddMovieForEvent(Activity context){
        this.context = context;
       // model = ModelImpl.getSingletonInstance(context);
    }
    @Override
    public void onClick(View v) {
        Intent i = new Intent(context, MovieActivity.class);
        context.startActivityForResult(i, 1);

    }



}
