package com.example.testing.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.testing.R;
import com.example.testing.View.MovieActivity;

public class ViewTemplateListener implements View.OnClickListener {

    Context context;

    public ViewTemplateListener(Context context){
        this.context = context;
    }
    @Override
    public void onClick(View v) {
                Intent intent = new Intent(context, MovieActivity.class);
                context.startActivity(intent);
                Toast.makeText(context, "back to movie", Toast.LENGTH_SHORT).show();


        }
}
