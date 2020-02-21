package com.example.testing.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.testing.View.AddMovie;

public class GotoAddMovieListener implements View.OnClickListener {


    private Context context;

    public GotoAddMovieListener(Context context){
        this.context = context;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, AddMovie.class);
        context.startActivity(intent);
    }
}
