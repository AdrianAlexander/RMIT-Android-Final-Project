package com.example.testing.listener;

import android.app.Activity;
import android.text.Editable;
import android.view.View;

import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;

public class AddMovieListener implements View.OnClickListener {

    private Model model;
    private Activity act;
    private Editable id;
    private Editable title;
    private Editable year;
    private Editable image;

    public AddMovieListener(Activity act, Editable id, Editable title, Editable year, Editable image){
        model = ModelImpl.getSingletonInstance(act);
        this.act = act;
        this.id = id;
        this. title = title;
        this.year = year;
        this.image = image;

    }

    @Override
    public void onClick(View v) {
        model.addMovie(id.toString(), title.toString(), Integer.parseInt(year.toString()), image.toString());
        act.finish();
    }


}
