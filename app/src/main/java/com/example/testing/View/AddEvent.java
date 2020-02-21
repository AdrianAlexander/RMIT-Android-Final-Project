package com.example.testing.View;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.testing.R;
import com.example.testing.listener.BacktoEventListListener;
import com.example.testing.listener.GotoAddMovieForEvent;
import com.example.testing.listener.okAddEventListener;
import com.example.testing.listener.okEditEventListener;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEvent extends AppCompatActivity {

    public EditText id, title, startDate, endDate, venue, location, movieTitle, movieYear, movieImage;
    Calendar calendar;
    Activity activity;
    SimpleDateFormat dateFormat;
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        model = ModelImpl.getSingletonInstance(this);
        activity = this;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a", Locale.getDefault());

        id = findViewById(R.id.addIdEvent);
        title = findViewById(R.id.addTitleEvent);
        startDate = findViewById(R.id.addDateStart);
        endDate = findViewById(R.id.addDateEnd);
        venue = findViewById(R.id.addVenue);
        location = findViewById(R.id.addLocation);
        movieTitle = findViewById(R.id.addEventMovieTitle);
        movieYear = findViewById(R.id.addEventMovieYear);
        movieImage = findViewById(R.id.addEventMovieImage);

        startDate.setOnClickListener(dateTextListener);
        endDate.setOnClickListener(dateTextListener2);

        Model model;

        Button add = findViewById(R.id.addEventOk);
        add.setOnClickListener(new okAddEventListener(this, 0, id.getText(), title.getText(), startDate.getText(), endDate.getText(), venue.getText(), location.getText(), movieTitle.getText(), movieYear.getText(), movieImage.getText()));

        Button addMov = findViewById(R.id.addMovee);
        addMov.setOnClickListener(new GotoAddMovieForEvent(this));
        Button cancel = findViewById(R.id.addEventCancel);
        cancel.setOnClickListener(new BacktoEventListListener(this));


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            Bundle event = getIntent().getExtras();

            String messageTitle = data.getStringExtra("title message");
            movieTitle.setText(messageTitle);
            String messageYear = data.getStringExtra("year message");
            movieYear.setText(messageYear);
            String messageImage = data.getStringExtra("image message");
            movieImage.setText(messageImage);
            String positionMovie = data.getStringExtra("position movie");



            //String position = event.getString("position");





            Button add = findViewById(R.id.addEventOk);
            add.setOnClickListener(new okAddEventListener(this, Integer.parseInt(positionMovie), id.getText(), title.getText(), startDate.getText(), endDate.getText(), venue.getText(), location.getText(), movieTitle.getText(), movieYear.getText(), movieImage.getText()));
        }

    }

    public View.OnClickListener dateTextListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            calendar = Calendar.getInstance();
            new DatePickerDialog(activity, dateData, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    };


    private DatePickerDialog.OnDateSetListener dateData = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            new TimePickerDialog(activity, timeData, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),false).show();
        }
    };

    private TimePickerDialog.OnTimeSetListener timeData = new TimePickerDialog.OnTimeSetListener() {


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(calendar.MINUTE, minute);
            startDate.setText(dateFormat.format(calendar.getTime()));
            model.addSetStartDate(dateFormat.format(calendar.getTime()));
        }
    };

    public View.OnClickListener dateTextListener2 = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            calendar = Calendar.getInstance();
            new DatePickerDialog(activity, dateData2, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    };


    private DatePickerDialog.OnDateSetListener dateData2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            new TimePickerDialog(activity, timeData2, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),false).show();
        }
    };

    private TimePickerDialog.OnTimeSetListener timeData2 = new TimePickerDialog.OnTimeSetListener() {


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(calendar.MINUTE, minute);
            endDate.setText(dateFormat.format(calendar.getTime()));
            model.addSetEndDate(dateFormat.format(calendar.getTime()));
        }
    };
}
