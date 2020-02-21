package com.example.testing.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public interface Event {

    String getId();

    String getTitle();

    void setTitle(String title);

    String getStartDate();

    Movie getMovie();

    void setMovie(Movie movie);

    void setStartDate(String date);

    String getEndDate();

    void setEndDate(String date);

    String getVenue();

    void setVenue(String venue);

    String getLocation();

    void setLocation(String location);

}
