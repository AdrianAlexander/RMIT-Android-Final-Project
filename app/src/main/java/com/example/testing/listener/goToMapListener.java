package com.example.testing.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.testing.View.Maps;
import com.example.testing.model.Event;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;

import java.util.List;

public class goToMapListener implements View.OnClickListener{

    private Context context;
    private int index;
    private List<Event> eventList;
    private Model model;

    public goToMapListener(Context context, int index){
        this.context = context;
        this.index = index;
        model = ModelImpl.getSingletonInstance(context);
        eventList = model.getEventList();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, Maps.class);
        intent.putExtra("venue", eventList.get(index).getVenue());
        intent.putExtra("location", eventList.get(index).getLocation());
        intent.putExtra("position", Integer.toString(index));
        context.startActivity(intent);
    }
}
