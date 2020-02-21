package com.example.testing.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.testing.Adapter.MovieAdapter;
import com.example.testing.R;
import com.example.testing.listener.GotoAddMovieListener;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.Movie;
import com.example.testing.model.ViewModel.MovieViewModel;

import java.util.ArrayList;


public class MovieActivity extends AppCompatActivity {
    private String TAG = getClass().getName();
    private Movie kentang;
    ArrayList<Movie> myDataset;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mRecyclerView = (RecyclerView)findViewById(R.id.movieRecycler);
        Model model = ModelImpl.getSingletonInstance(MovieActivity.this);
        myDataset = model.getItemList();




        MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);//refer ke view model

        movieViewModel.getItems().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Movie> movies) {
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(MovieActivity.this);
                mAdapter = new MovieAdapter(MovieActivity.this, movies);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter );
            }
        });

        Button btnAdd = findViewById(R.id.addButtonMovie);
        btnAdd.setOnClickListener(new GotoAddMovieListener(this));


       /* mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MovieAdapter(this, myDataset);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter );*/





    }
}
