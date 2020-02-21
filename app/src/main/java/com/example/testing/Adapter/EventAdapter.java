package com.example.testing.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testing.R;
import com.example.testing.listener.DeleteEventListener;
import com.example.testing.listener.GotoAddEventListener;
import com.example.testing.listener.GotoEventEditListener;
import com.example.testing.listener.goToDistanceMatrix;
import com.example.testing.listener.goToMapListener;
import com.example.testing.model.Event;
import com.example.testing.model.Movie;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private String TAG = getClass().getName();
    private ArrayList<Event> myDatasets;
    private Context context;


    public EventAdapter(Context context, ArrayList<Event> myDatasets) {
        this.context = context;
        this.myDatasets = myDatasets;

    }

    @Override
    public EventAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_event, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(EventAdapter.MyViewHolder holder, int position) {

        holder.buttonEdit.setTag(myDatasets.get(position));
        holder.titleText.setText(myDatasets.get(position).getTitle());
        holder.startDate.setText(myDatasets.get(position).getStartDate().toString());
        holder.endDate.setText(myDatasets.get(position).getEndDate().toString());
        holder.venue.setText(myDatasets.get(position).getVenue());
        holder.location.setText(myDatasets.get(position).getLocation());
        holder.buttonDelete.setOnClickListener(new DeleteEventListener(context, position));
        holder.map.setOnClickListener(new goToMapListener(context, myDatasets.indexOf(holder.buttonEdit.getTag())));
        holder.buttonEdit.setOnClickListener(new GotoEventEditListener(context, myDatasets.indexOf(holder.buttonEdit.getTag())));
        holder.matrix.setOnClickListener(new goToDistanceMatrix(context, myDatasets.indexOf(holder.buttonEdit.getTag())));
        try{
            Uri imgUri = Uri.parse("android.resource://" + context.getPackageName() +"/drawable/" + myDatasets.get(position).getMovie().getImage().toLowerCase());
            holder.movieTitle.setText(myDatasets.get(position).getMovie().getTitle());
            holder.movieYear.setText(Integer.toString(myDatasets.get(position).getMovie().getYear()));
            holder.img.setImageURI(imgUri);
        }catch (NullPointerException e){

        }
    }

    @Override
    public int getItemCount() {
        return myDatasets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleText;
        public TextView startDate;
        public TextView endDate;
        public TextView venue;
        public TextView location;
        public Button buttonEdit;
        public Button buttonDelete;
        public Button buttonAdd;
        public TextView movieTitle;
        public TextView movieYear;
        public ImageView img;
        public Button map, matrix;

        public MyViewHolder(View v) {
            super(v);
            titleText = v.findViewById(R.id.titleEventText);
            startDate = v.findViewById(R.id.startDateText);
            endDate = v.findViewById(R.id.endDateText);
            venue = v.findViewById(R.id.venue);
            location = v.findViewById(R.id.location);
            buttonEdit = v.findViewById(R.id.editEvent);
            buttonDelete = v.findViewById(R.id.deleteEvent);
            movieTitle = v.findViewById(R.id.movieTitleEvent);
            movieYear = v.findViewById(R.id.yearMovieEvent);
            img = v.findViewById(R.id.imageEvent);
            map = v.findViewById(R.id.mapButton);
            matrix = v.findViewById(R.id.distanceMatrix);
        }


    }
}
