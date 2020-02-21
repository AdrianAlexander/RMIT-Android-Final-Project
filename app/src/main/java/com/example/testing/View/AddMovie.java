package com.example.testing.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.testing.R;
import com.example.testing.listener.AddMovieListener;
import com.example.testing.listener.ViewTemplateListener;

public class AddMovie extends AppCompatActivity {

    public EditText id;
    public EditText title;
    public EditText year;
    public EditText image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);


        id = findViewById(R.id.idM);
        title = findViewById(R.id.titleM);
        year = findViewById(R.id.yearM);
        image = findViewById(R.id.imageM);

       Button cancel = findViewById(R.id.cancelButton);
       Button add = findViewById(R.id.addButon);
       cancel.setOnClickListener(new ViewTemplateListener(this));
       add.setOnClickListener(new AddMovieListener(this, id.getText(), title.getText(), year.getText(), image.getText()));
    }
}
