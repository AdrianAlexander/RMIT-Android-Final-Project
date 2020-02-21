package com.example.testing.View;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.testing.R;
import com.example.testing.listener.BacktoEventListListener;
import com.example.testing.listener.GotoAddMovieForEvent;
import com.example.testing.listener.GotoContacts;
import com.example.testing.listener.okEditEventListener;
import com.example.testing.model.Event;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class EditEvent extends AppCompatActivity{
    public EditText eventTitle;
    public EditText eventStartDate;
    public EditText eventEndDate;
    public EditText eventVenue;
    public EditText eventLocation;
    public EditText movieTitle;
    public EditText movieYear;
    public EditText movieImage;
    public EditText attendes;
    private Calendar calendar;
    private Activity activity;
    private SimpleDateFormat dateFormat;
    static final int REQUEST_CODE = 0;
    private Model model;
    private String pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        model = ModelImpl.getSingletonInstance(this);

        eventTitle = findViewById(R.id.editTitle);
        eventStartDate = findViewById(R.id.editDateStart);
        eventEndDate = findViewById(R.id.editDateEnd);
        eventVenue = findViewById(R.id.editVenue);
        eventLocation = findViewById(R.id.editLocation);
        movieTitle = findViewById(R.id.editMovieTitle);
        movieYear = findViewById(R.id.editMovieYear);
        movieImage = findViewById(R.id.editMovieImage);
        attendes = findViewById(R.id.addAttendes);


        activity = this;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy h:mm a", Locale.getDefault());


        Bundle event = getIntent().getExtras();
        if (event == null) {
            return;
        }

        String title = event.getString("event title");
        String startDate = event.getString("date start");
        String endDate = event.getString("date end");
        String venue = event.getString("venue");
        String location = event.getString("location");
        //String movie = event.getString("title message");
        //String year = event.getString("year message");
        String position = event.getString("position");
        this.pos = position;



        //Intent intent = getIntent();

        //String movie = event.getString("title");

        //String pos = event.getString("position");


        eventTitle.setText(title);
        eventStartDate.setText(startDate);
        eventEndDate.setText(endDate);
        eventVenue.setText(venue);
        eventLocation.setText(location);


        eventEndDate.setOnClickListener(dateTextListener2);
        eventStartDate.setOnClickListener(dateTextListener);

        Button cancelButton = findViewById(R.id.editEventCancel);
        cancelButton.setOnClickListener(new BacktoEventListListener(this));


        Button addMovie = findViewById(R.id.addMov);
        addMovie.setOnClickListener(new GotoAddMovieForEvent(this));

        Button attendes = findViewById(R.id.attendes);
        attendes.setOnClickListener(new GotoContacts(this));


        Button ok = findViewById(R.id.editEventOk);
        ok.setOnClickListener(new okEditEventListener(this, Integer.parseInt(position), 0, eventTitle.getText(), eventStartDate.getText(), eventEndDate.getText(), eventVenue.getText(), eventLocation.getText(), movieTitle.getText(), movieYear.getText(), movieImage.getText()));



    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            Bundle event = getIntent().getExtras();

            String messageTitle = data.getStringExtra("title message");
            movieTitle.setText(messageTitle);
            String messageYear = data.getStringExtra("year message");
            movieYear.setText(messageYear);
            String positionMovie = data.getStringExtra("position movie");
            String messageImage = data.getStringExtra("image message");
            movieImage.setText(messageImage);








            Button ok = findViewById(R.id.editEventOk);
            ok.setOnClickListener(new okEditEventListener(this, Integer.parseInt(pos), Integer.parseInt(positionMovie), eventTitle.getText(), eventStartDate.getText(), eventEndDate.getText(), eventVenue.getText(), eventLocation.getText(), movieTitle.getText(), movieYear.getText(), movieImage.getText()));
        }else if(requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contactData = data.getData();
                Cursor c = getContentResolver().query(contactData, null, null, null, null);
                if (c.moveToFirst()) {
                    String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    attendes.setText(name);
                }
            }
        }





    }


    public View.OnClickListener dateTextListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            calendar = Calendar.getInstance();
            new DatePickerDialog(activity, dateData, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    };


    public DatePickerDialog.OnDateSetListener dateData = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            new TimePickerDialog(activity, timeData, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),false).show();
        }
    };

    public TimePickerDialog.OnTimeSetListener timeData = new TimePickerDialog.OnTimeSetListener() {


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Bundle event = getIntent().getExtras();
            String position = event.getString("position");
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(calendar.MINUTE, minute);
            eventStartDate.setText(dateFormat.format(calendar.getTime()));
            model.setStartDate(Integer.parseInt(position),dateFormat.format(calendar.getTime() ));
        }
    };

    public View.OnClickListener dateTextListener2 = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            calendar = Calendar.getInstance();
            new DatePickerDialog(activity, dateData2, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    };


    public DatePickerDialog.OnDateSetListener dateData2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            new TimePickerDialog(activity, timeData2, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),false).show();
        }
    };

    public TimePickerDialog.OnTimeSetListener timeData2 = new TimePickerDialog.OnTimeSetListener() {


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Bundle event = getIntent().getExtras();
            String position = event.getString("position");
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(calendar.MINUTE, minute);
            eventEndDate.setText(dateFormat.format(calendar.getTime()));
            model.setEndDate(Integer.parseInt(position), dateFormat.format(calendar.getTime()));

        }
    };


}
