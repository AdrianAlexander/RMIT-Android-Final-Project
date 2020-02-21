package com.example.testing.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.testing.View.EditEvent;
import com.example.testing.View.viewTemplate;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.Movie;

import java.util.List;

public class sendMovietoEditListener implements View.OnClickListener {


    private int edit;
    private Activity context;
    Model model;
    List<Movie> movieList;

    public sendMovietoEditListener(int edit, Activity context){
        this.edit = edit;
        this.context = context;
        model = ModelImpl.getSingletonInstance(context);
        movieList = model.getItemList();
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, EditEvent.class);
        intent.putExtra("id message", movieList.get(edit).getId());
        intent.putExtra("title message", movieList.get(edit).getTitle());
        intent.putExtra("year message", Integer.toString(movieList.get(edit).getYear()));
        intent.putExtra("image message", movieList.get(edit).getImage());
        intent.putExtra("position movie",Integer.toString(edit));
        context.setResult(1, intent);
        context.finish();
        Toast.makeText(context, "Edit List", Toast.LENGTH_SHORT).show();
    }
}
