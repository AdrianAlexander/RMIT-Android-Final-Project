package com.example.testing.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.nfc.Tag;
import android.util.Log;

import com.example.testing.model.Event;
import com.example.testing.model.EventImpl;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.Movie;
import com.example.testing.model.MovieImpl;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper{

    private String TAG = getClass().getName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movieEvent";
    public static final String TABLE_MOVIE = "movies";
    public static final String COLUMN_ID = "idMovies";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_IMAGE = "image";
    public static final String TABLE_EVENT = "events";
    public static final String COLUMN_ID_EVENT = "id_event";
    public static final String COLUMN_TITLE_EVENT = "title_event";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_VENUE = "venue";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_MOVIE_IN_EVENT = "movie_id";
    public static final String ANDROID_ID = "id";
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();
    private Model model;
    private Context context;





    public DB(Context context){
        //this.context = context;
        //model = ModelImpl.getSingletonInstance(context);
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //this.context = context;
        //model = ModelImpl.getSingletonInstance(this.context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryMovie = "CREATE TABLE IF NOT EXISTS " + TABLE_MOVIE + "(" +
                //ANDROID_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ID + " TEXT PRIMARY KEY NOT NULL, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_YEAR + " INTEGER, " +
                COLUMN_IMAGE + " TEXT" + ")";

        String queryEvent = "CREATE TABLE IF NOT EXISTS " + TABLE_EVENT + "(" +
                COLUMN_ID_EVENT + " TEXT PRIMARY KEY NOT NULL, " +
                COLUMN_TITLE_EVENT + " TEXT, " +
                COLUMN_START_DATE + " TEXT, " +
                COLUMN_END_DATE + " TEXT, " +
                COLUMN_VENUE + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_MOVIE_IN_EVENT + " TEXT, FOREIGN KEY (" + COLUMN_MOVIE_IN_EVENT + ") REFERENCES " + TABLE_MOVIE + " (" + COLUMN_ID +"))";

        db.execSQL(queryMovie);
        db.execSQL(queryEvent);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        onCreate(db);

    }

    public void addMovie(Movie movie){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, movie.getId());
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_YEAR, movie.getYear());
        values.put(COLUMN_IMAGE, movie.getImage());


        //db.insert(TABLE_MOVIE, null, values);
       // db.execSQL("INSERT INTO " + TABLE_MOVIE + " (" + COLUMN_ID +", " + COLUMN_TITLE +", " + COLUMN_YEAR + ", " + COLUMN_IMAGE +") VALUES("
        //+ movie.getId() +", " + movie.getTitle() +", " +movie.getYear() +", " + movie.getImage() +")");
        //db.close();
        Cursor c=db.rawQuery("SELECT * FROM " + TABLE_MOVIE + " WHERE " + COLUMN_ID + "=\"" + movie.getId() + "\";", null);
        if(c.moveToFirst() || c.moveToNext())
        {
            Log.i(TAG, "record exists"); //showMessage("Error", "Record exist");
        }
        else
        {
            db.insert(TABLE_MOVIE, null, values);
            db.close();
            Log.i(TAG, "added");
        }

    }

    /*public void deleteMovie(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MOVIE + " WHERE " + COLUMN_ID + "= '"  + id +  "'");
        db.close();
    }

    public void editMovie(String id, String title, int year, String image){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_IMAGE, image);
        db.update(TABLE_MOVIE, values, "ide  = ?",new String[] { id });
        db.close();
    }*/

    public void addEvent(Event event){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_EVENT, event.getId());
        values.put(COLUMN_TITLE_EVENT, event.getTitle());
        values.put(COLUMN_START_DATE, event.getStartDate());
        values.put(COLUMN_END_DATE, event.getEndDate());
        values.put(COLUMN_VENUE, event.getVenue());
        values.put(COLUMN_LOCATION, event.getLocation());
        try {
            values.put(COLUMN_MOVIE_IN_EVENT, event.getMovie().getId());
        }catch (Exception e){

        }
        Cursor c=db.rawQuery("SELECT * FROM " + TABLE_EVENT + " WHERE " + COLUMN_ID_EVENT + "=\"" + event.getId() + "\";", null);

        if(c.moveToFirst() || c.moveToNext())
        {
            Log.i(TAG, "record exists"); //showMessage("Error", "Record exist");
        }
        else
        {
            db.insert(TABLE_EVENT, null, values);
            db.close();
            Log.i(TAG, "added");
        }

    }

    public void deleteTable(){
        SQLiteDatabase db = getWritableDatabase();
        String deleteQueryEvent ="DELETE FROM " + TABLE_EVENT + ";";
        String deleteQueryMovie = "DELETE FROM " + TABLE_MOVIE + ";";
        db.execSQL(deleteQueryEvent);
        db.execSQL(deleteQueryMovie);
        db.close();
        Log.i(TAG, "table deleted");
    }


    public void saveDatabase(){
        model = ModelImpl.getSingletonInstance(context);
        events = model.getEventList();
        movies = model.getItemList();


       for(Event event : events){
           this.addEvent(event);
       }

       for(Movie movie: movies){
           this.addMovie(movie);
       }

       Log.i(TAG, "data saved");

    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        String[] collumns = new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_YEAR, COLUMN_IMAGE};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_MOVIE, collumns, null,null,null,null,null);

        int id = cursor.getColumnIndex(COLUMN_ID);
        int title = cursor.getColumnIndex(COLUMN_TITLE);
        int year = cursor.getColumnIndex(COLUMN_YEAR);
        int image = cursor.getColumnIndex(COLUMN_IMAGE);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            movies.add(new MovieImpl(cursor.getString(id), cursor.getString(title), cursor.getInt(year), cursor.getString(image)));
        }

        cursor.close();
        return movies;

    }


    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();
        String[] collumns = new String[]{COLUMN_ID_EVENT, COLUMN_TITLE_EVENT, COLUMN_START_DATE, COLUMN_END_DATE, COLUMN_VENUE, COLUMN_LOCATION};

        // Select All Query
        //String selectQuery = "SELECT  * FROM " + TABLE_MOVIE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_EVENT, collumns, null,null,null,null,null);

        int id = cursor.getColumnIndex(COLUMN_ID_EVENT);
        int title = cursor.getColumnIndex(COLUMN_TITLE_EVENT);
        int startDate = cursor.getColumnIndex(COLUMN_START_DATE);
        int endDate = cursor.getColumnIndex(COLUMN_END_DATE);
        int venue = cursor.getColumnIndex(COLUMN_VENUE);
        int location = cursor.getColumnIndex(COLUMN_LOCATION);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            events.add(new EventImpl(cursor.getString(id), cursor.getString(title), cursor.getString(startDate), cursor.getString(endDate), cursor.getString(venue), cursor.getString(location)));
        }

        cursor.close();
        return events;

    }



}
