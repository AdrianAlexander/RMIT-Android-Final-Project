package com.example.testing.model;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.testing.Database.DB;
import com.example.testing.R;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map;

public class ModelImpl implements Model {

    private static final ModelImpl ourInstance = new ModelImpl();

    private final String TAG = getClass().getName();

    private ArrayList<Movie> movies = new ArrayList<>();

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private ArrayList<Event> events = new ArrayList<>();

    private ArrayList<Contact> contacts = new ArrayList<>();

    private String startDateTemp;

    private String endDateTemp;

    private static Context applicationContext;

    private DB db = new DB(applicationContext);;


    private ModelImpl() {
        loadItems();
        loadEvents();
    }


    private static class LazyHolder {
        static final Model INSTANCE = new ModelImpl();
    }

    public static Model getSingletonInstance(Context appContext) {
        if(applicationContext == null) {
            applicationContext = appContext;
        }
        return LazyHolder.INSTANCE;
    }

    @Override
    public ArrayList<Movie> getItemList() {
        return movies;
    }

    @Override
    public ArrayList<Event> getEventList() {
        return events;
    }

    @Override
    public ArrayList<Contact> getContactList() {
        return contacts ;
    }


    @Override
    public Event getItembyEventId(int id) {
        return events.get(id);
    }

    @Override
    public Movie getItembyId(int id) {
        return movies.get(id);
    }


    private void loadItems() {
        movies.clear();
        Scanner scanner = null;



        try {
            scanner = new Scanner(applicationContext.getResources().openRawResource(R.raw.movie));
            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                line = line.trim().replace("\"", "");
                String[] components = line.split(",");

                //movies.add(new MovieImpl(components[0],components[1],Integer.parseInt(components[2]),components[3]));

                db.addMovie(new MovieImpl(components[0],components[1],Integer.parseInt(components[2]),components[3]));

            }
            this.movies = db.getAllMovies();

        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        } finally {
            if(scanner != null) {
                scanner.close();
            }

        }
    }

    public void loadEvents(){
        events.clear();
        Scanner scanner = null;


        try {
            scanner = new Scanner(applicationContext.getResources().openRawResource(R.raw.event));
            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                line = line.trim().replace("\"", "");
                String[] components = line.split(",");

                //events.add(new EventImpl(components[0],components[1],(components[2]),(components[3]),components[4],components[5]));
                db.addEvent(new EventImpl(components[0],components[1],(components[2]),(components[3]),components[4],components[5]));
            }
            this.events = db.getAllEvents();
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        } finally {
            if(scanner != null) {
                scanner.close();

            }

        }
    }




    public void addPropertyChangeListener(PropertyChangeListener listener){
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void editMovie(int movieId, String title, int year) {
        Movie movie = movies.get(movieId);
        movie.setTitle(title);
        movie.setYear(year);
        propertyChangeSupport.firePropertyChange("update movie", false,
                movie);
    }

    public void addMovie(String id, String title, int year, String image){
        Movie movie = new MovieImpl(id, title, year, image);
        movies.add(movie);
        propertyChangeSupport.firePropertyChange("add movie", false, movie);
    }

    @Override
    public void editEvent(int eventId, String title, String startDate, String endDate, String venue, String location, String movieTitle, int movieYear, String movieImage) {
        Event event = events.get(eventId);
        event.setTitle(title);
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setVenue(venue);
        event.setLocation(location);
        event.getMovie().setTitle(movieTitle);
        event.getMovie().setYear(movieYear);
        event.getMovie().setImage(movieImage);
        //setMovieTitle(eventId, movieTitle );
        propertyChangeSupport.firePropertyChange("edit event", false, event);
    }

    public void deleteMovie(int movieId){
        Movie movie = movies.get(movieId);
        movies.remove(movie);
        propertyChangeSupport.firePropertyChange("delete movie", false, movie );
    }

    @Override
    public void addEvent(String id, String title, String startDate, String endDate, String venue, String location, String movieTitle, int movieYear, Movie mov, String movieImage) {
        Event event = new EventImpl(id, title, startDate, endDate, venue, location);
        events.add(event);
        event.setMovie(mov);

        event.getMovie().setTitle(movieTitle);
        event.getMovie().setYear(movieYear);
        event.getMovie().setImage(movieImage);
        propertyChangeSupport.firePropertyChange("add event", false, event);
    }


    @Override
    public void deleteEvent(int eventId) {
        Event event = events.get(eventId);
        events.remove(event);
        propertyChangeSupport.firePropertyChange("delete event", false , event);
    }

    public String getStartDate(int index){
        return events.get(index).getStartDate();
    }

    public String getEndDate(int index){
        return events.get(index).getEndDate();
    }


    public void setStartDate(int index,String startDate){
        events.get(index).setStartDate(startDate);
    }

    public void setEndDate(int index, String endDate){
        events.get(index).setEndDate(endDate);
    }

    @Override
    public void addSetStartDate(String startDate) {
        startDateTemp = startDate;
    }

    @Override
    public void addSetEndDate(String endDate) {
        endDateTemp = endDate;
    }

    @Override
    public String addGetStartDate() {
        return startDateTemp;
    }

    @Override
    public String addGetEndDate() {
        return endDateTemp;
    }


    @Override
    public void createMovie(int index, Movie movie){
       events.get(index).setMovie(movie);
    }

    @Override
    public String getMovieTitle(int index) {
        return movies.get(index).getTitle();
    }

    @Override
    public void addContacttoArrayList(ArrayList<Contact> contact) {
        contacts.addAll(contact);
    }

    @Override
    public String getMovieImage(int index) {
        return movies.get(index).getImage();
    }

    @Override
    public int getMovieYear(int index){
        return movies.get(index).getYear();
    }



}
