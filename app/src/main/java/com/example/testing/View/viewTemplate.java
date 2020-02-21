package com.example.testing.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.testing.R;
import com.example.testing.listener.okEditMovieListener;
import com.example.testing.listener.ViewTemplateListener;

public class viewTemplate extends AppCompatActivity {
    public EditText movieTitle;
    public EditText movieYear;
    private ViewTemplateListener listen = new ViewTemplateListener(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_template);

        movieTitle= findViewById(R.id.movieText);
        movieYear = findViewById(R.id.yearText);

        Bundle movie = getIntent().getExtras();
        if(movie == null){
            return;
        }
        String title = movie.getString("title");
        String year = movie.getString("year");
        String position = movie.getString("position");


        //TextView movieTitle =(TextView)findViewById(R.id.movieText);
        //TextView movieYear = (TextView) findViewById(R.id.yearText);

        movieTitle.setText(title);
        movieYear.setText(year);

        Button okButton = findViewById(R.id.myOkButton);
        Button cancelButton = findViewById(R.id.myCancelButton);

        okButton.setOnClickListener(new okEditMovieListener(this, Integer.parseInt(position), movieTitle.getText(), movieYear.getText()));
        cancelButton.setOnClickListener(listen);


    }



}
