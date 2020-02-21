package com.example.testing.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.testing.Adapter.EventAdapter;
import com.example.testing.Adapter.MovieAdapter;
import com.example.testing.Database.DB;
import com.example.testing.Database.DBThread.threadDB;
import com.example.testing.R;
import com.example.testing.listener.GotoAddEventListener;
import com.example.testing.listener.goToMapListener;
import com.example.testing.model.Event;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.Movie;
import com.example.testing.model.ViewModel.EventViewModel;
import com.example.testing.model.ViewModel.MovieViewModel;


import java.util.ArrayList;

public class EventRecycler extends AppCompatActivity {

    private String TAG = getClass().getName();
    ArrayList<Event> myDataset;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<Movie> moviesData;
    String title;
    String year;
    DB db = new DB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_recycler);



        mRecyclerView = (RecyclerView)findViewById(R.id.eventRecycler);
        Model model = ModelImpl.getSingletonInstance(EventRecycler.this);
        myDataset = model.getEventList();





        EventViewModel eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);//refer ke view model

        eventViewModel.getItems().observe(this, new Observer<ArrayList<Event>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Event> events) {
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(EventRecycler.this);
                mAdapter = new EventAdapter(EventRecycler.this, events);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter );
            }
        });

        Button buttonAdd = findViewById(R.id.addEvent);
        buttonAdd.setOnClickListener(new GotoAddEventListener(this));


    }

    @Override
    protected void onStop(){
        super.onStop();
        new Thread(new threadDB(db)).start();
    }
}
