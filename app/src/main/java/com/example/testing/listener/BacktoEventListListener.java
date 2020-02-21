package com.example.testing.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.testing.View.EventRecycler;

public class BacktoEventListListener implements View.OnClickListener {

    Context context;

    public BacktoEventListListener(Context context){
        this.context = context;
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, EventRecycler.class);
        context.startActivity(intent);
        Toast.makeText(context, "Back to event list",Toast.LENGTH_SHORT).show();
    }
}
