package com.example.testing.listener;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.testing.View.ShowDistanceMatrix;
import com.example.testing.model.Event;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class goToDistanceMatrix implements View.OnClickListener {

    private Context context;

    private final String TAG = getClass().getName();

    private Model model;

    private ArrayList<Event> eventList;

    private int index;




    public goToDistanceMatrix(Context context, int index){
        this.context = context;
        this.index = index;
        model = ModelImpl.getSingletonInstance(context);
        eventList = model.getEventList();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ShowDistanceMatrix.class);
        intent.putExtra("location", eventList.get(index).getLocation());
        intent.putExtra("position", Integer.toString(index));
        context.startActivity(intent);
    }


}
