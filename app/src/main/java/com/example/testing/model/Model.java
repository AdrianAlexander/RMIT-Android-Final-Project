package com.example.testing.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Model {


    ArrayList<Movie> getItemList();

    ArrayList<Event> getEventList();

    ArrayList<Contact> getContactList();

    Event getItembyEventId(int id);

    Movie getItembyId(int id);

    void deleteEvent(int eventId);

    void editMovie(int movieId, String title, int year);

    void addPropertyChangeListener(PropertyChangeListener listener);

    void addMovie(String id, String title, int year, String image);

    void editEvent(int eventId, String title, String startDate, String endDate, String venue, String location, String movieTitle, int movieYear, String movieImage);

    void deleteMovie(int movieId);

    void addEvent(String id, String title, String startDate, String endDate, String venue, String location, String movieTitle, int MovieYear, Movie mov, String movieImage);

    void setStartDate(int index, String startDate);

    void setEndDate(int index, String endDate);

    String getStartDate(int index);

    String getEndDate(int index);

    void addSetStartDate(String startDate);

    void addSetEndDate(String endDate);

    String addGetStartDate();

    String addGetEndDate();

    void createMovie(int index, Movie movie);

    int getMovieYear(int index);

    String getMovieImage(int index);

    String getMovieTitle(int index);

    void addContacttoArrayList(ArrayList<Contact> contact);

}
