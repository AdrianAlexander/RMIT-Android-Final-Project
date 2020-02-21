package com.example.testing.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.testing.View.EditEvent;
import com.example.testing.model.Event;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;

import java.util.List;

public class GotoEventEditListener implements View.OnClickListener{

    private int edit;
    private Context context;
    Model model;
    List<Event> eventList;

    public GotoEventEditListener(Context context, int edit){
        this.context = context;
        this.edit = edit;
        model = ModelImpl.getSingletonInstance(context);
        eventList = model.getEventList();
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, EditEvent.class);
        intent.putExtra("event title", eventList.get(edit).getTitle());
        intent.putExtra("date start", eventList.get(edit).getStartDate().toString());
        intent.putExtra("date end", eventList.get(edit).getEndDate().toString());
        intent.putExtra("venue", eventList.get(edit).getVenue());
        intent.putExtra("location", eventList.get(edit).getLocation());
        intent.putExtra("position", Integer.toString(edit));
        context.startActivity(intent);
        Toast.makeText(context, "edit event", Toast.LENGTH_SHORT);

    }
}
