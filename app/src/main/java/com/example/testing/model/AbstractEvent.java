package com.example.testing.model;

import com.example.testing.model.Event;

import java.util.ArrayList;
import java.util.Date;

public class AbstractEvent implements Event {

    private String id;
    private String title;
    private String startDate;
    private String endDate;
    private String venue;
    private String location;
    private Movie movie;

    public AbstractEvent(String id, String title, String startDate, String endDate, String venue, String location){
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.venue = venue;
        this.location = location;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public Movie getMovie() {
        return movie;
    }

    @Override
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void setStartDate(String date) {
        startDate = date;
    }

    @Override
    public String getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(String date) {
        endDate = date;
    }

    @Override
    public String getVenue() {
        return venue;
    }

    @Override
    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    public String toSring(){
        return String.format("Id = %s, title = %s, start date = %s, end date = %s, venue = %s, location = %s", id, title, startDate, endDate, venue, location);
    }
}
