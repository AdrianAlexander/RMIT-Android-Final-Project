package com.example.testing.listener;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;

public class DeleteEventListener implements View.OnClickListener {

    private Context act;
    private int eventId;
    Model model;

    public DeleteEventListener(Context act, int eventId){
        this.act = act;
        this.eventId = eventId;
        model = ModelImpl.getSingletonInstance(act);
    }

    @Override
    public void onClick(View v) {
        model.deleteEvent(eventId);
    }
}
