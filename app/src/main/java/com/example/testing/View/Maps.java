package com.example.testing.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testing.R;
import com.example.testing.model.Event;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class Maps extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;

    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private static final float DEFAULT_ZOOM = 15;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private boolean mLocationPermissionGranted = false;

    private GoogleMap mMap;

    private String position;

    private String venue;

    private String location;

    private String location1;

    private String location2;

    private static final int LATITUDE = 0;

    private static final int LONGITUDE = 1;

    private int index ;


    private Model model;

    private ArrayList<Event> events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Bundle event = getIntent().getExtras();
        if (event == null) {
            return;
        }

        String venue = event.getString("venue");
        String location = event.getString("location");
        String position = event.getString("position");
        this.position = position;
        this.location = location;
        this.venue = venue;
        model = ModelImpl.getSingletonInstance(this);
        this.events = model.getEventList();
        //splitLocation();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapp);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        Button next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // index = Integer.parseInt(position);
                if(index > events.size() - 2){
                    index = 0;
                    moveCamera(index);
                }else{
                    index += 1;
                    moveCamera(index);
                }



                Log.i(TAG, String.valueOf(index));


            }
        });


    }


    private LatLng locationString(String location){
        String locate[] = location.split(" ");
        return new LatLng(Double.parseDouble(locate[LATITUDE].trim()), Double.parseDouble(locate[LONGITUDE].trim()) );
    }

    private MarkerOptions marker(Event event){
        LatLng location = locationString(event.getLocation());
        MarkerOptions options = new MarkerOptions().position(location).title(event.getVenue() + " " + event.getTitle());
        return options;

    }


   public void moveCamera(int index){
        LatLng latlng = locationString(events.get(index).getLocation());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, DEFAULT_ZOOM));
   }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "map ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: Map ready");
        mMap = googleMap;

        for (Event event : this.events){
            MarkerOptions markerOptions = marker(event);
            mMap.addMarker(markerOptions);
        }

        moveCamera(Integer.parseInt(position));


    }
}
