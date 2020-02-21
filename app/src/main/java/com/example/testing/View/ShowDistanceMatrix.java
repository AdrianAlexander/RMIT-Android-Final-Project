package com.example.testing.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.DistanceMatrix.DistanceMatrix;
import com.example.testing.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class ShowDistanceMatrix extends AppCompatActivity implements DistanceMatrix.Geo {
    private String TAG = getClass().getName();
    private static final int PERMISSION = 1;
    TextView distance;
    TextView time;
    EditText currentLocation;
    EditText destination;
    Button calculate;
    private double latitude;
    private double longitude;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private String position;
    private String location;
    private String location1, location2;
    private String original, dest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_distance_matrix);



        distance = findViewById(R.id.distance);
        time = findViewById(R.id.time);
        currentLocation = findViewById(R.id.currentLocation);
        destination = findViewById(R.id.destination);

        Bundle event = getIntent().getExtras();
        if (event == null) {
            return;
        }
        getLocation();
        String location = event.getString("location");
        String position = event.getString("position");
        this.position = position;
        this.location = location;

       String test = "-37.80828,144.96646";
       currentLocation.setText(test);

        splitLocation();

       destination.setText(location1+","+location2);


        calculate = findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                original = currentLocation.getText().toString();
                dest = destination.getText().toString();
                String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + original + "&destinations=" + dest + "&mode=driving&language=fr-FR&avoid=tolls&key=AIzaSyBmg3S6Gsc1E379YQht4t-1lD9dIuTcemA";
                new DistanceMatrix(ShowDistanceMatrix.this).execute(url);
            }
        });



    }

    public void getLocation(){
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if(ActivityCompat.checkSelfPermission(ShowDistanceMatrix.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED  && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermission();
        }
        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(ShowDistanceMatrix.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!= null){
                    currentLocation.setText(location.getLatitude()+","+location.getLongitude());
                }else{
                    Log.d(TAG, "no location");
                }
            }
        });
    }

    public void splitLocation(){
        String[] locate = location.split(" ");
        this.location1 = locate[0];
        this.location2 = locate[1];
    }


    @Override
    public void setDouble(String result) {
        String res[] = result.split(",");
        Double min = Double.parseDouble(res[0]) / 60;
        int dist = Integer.parseInt(res[1]) / 1000;
        time.setText("Duration= " + (int) (min / 60) + " hr " + (int) (min % 60) + " mins");
        distance.setText("Distance= " + dist + " kilometers");

    }

    private void requestPermission(){
            ActivityCompat.requestPermissions(ShowDistanceMatrix.this, new String[]{ACCESS_FINE_LOCATION}, PERMISSION);
    }
}
