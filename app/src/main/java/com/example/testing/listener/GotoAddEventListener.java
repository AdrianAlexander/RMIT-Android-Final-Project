package com.example.testing.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.testing.View.AddEvent;
import com.example.testing.View.AddMovie;
import com.example.testing.View.MovieActivity;

public class GotoAddEventListener implements View.OnClickListener{


    private Context context;

    public GotoAddEventListener(Context context){
        this.context = context;
        // model = ModelImpl.getSingletonInstance(context);
    }
    @Override
    public void onClick(View v) {
        Intent i = new Intent(context, AddEvent.class);
        context.startActivity(i);

    }


}
