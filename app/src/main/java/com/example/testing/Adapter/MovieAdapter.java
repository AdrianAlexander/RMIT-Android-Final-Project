package com.example.testing.Adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.testing.R;
import com.example.testing.listener.DeleteMovieListener;
import com.example.testing.listener.GotoEditMovieListener;
import com.example.testing.listener.sendMovietoAddListener;
import com.example.testing.listener.sendMovietoEditListener;
import com.example.testing.model.Movie;

import java.util.ArrayList;




public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private String TAG = getClass().getName();
    private ArrayList<Movie> myDatasets;
    //private Context context;
    private Activity context;




    /*public MovieAdapter(Context context, ArrayList<Movie> myDatasets){
        this.context = context;
        this.myDatasets = myDatasets;
    }*/

    public MovieAdapter(Activity context, ArrayList<Movie> myDataset){
      myDatasets  = myDataset;
      this.context = context;
    }


    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MyViewHolder holder, int position) {
      // Movie current = myDatasets.get(position);
      // holder.titleText.setText(current.getTitle());
       //holder.yearText.setText(current.getYear());
        Uri imgUri = Uri.parse("android.resource://" + context.getPackageName() +"/drawable/" + myDatasets.get(position).getImage().toLowerCase());
        holder.titleText.setText(myDatasets.get(position).getTitle());
        holder.yearText.setText(Integer.toString(myDatasets.get(position).getYear()));
        holder.movieEdit.setTag(myDatasets.get(position));
        holder.movieAddtoEditEvent.setTag(myDatasets.get(position));
        //holder.btn2.setOnClickListener(new GotoEditMovieListener(myDatasets.indexOf(holder.btn2.getTag()), context));
        holder.movieEdit.setOnClickListener(new GotoEditMovieListener(myDatasets.indexOf(holder.movieEdit.getTag()), context));
        holder.movieAddtoEditEvent.setOnClickListener(new sendMovietoEditListener(myDatasets.indexOf(holder.movieAddtoEditEvent.getTag()), context));
        holder.movieAddtoEditEvent.setOnClickListener(new sendMovietoAddListener(myDatasets.indexOf(holder.movieAddtoEditEvent.getTag()), context));
        holder.movieDelete.setOnClickListener(new DeleteMovieListener(context, position));
        holder.img.setImageURI(imgUri);






    }

    @Override
    public int getItemCount() {
        return myDatasets.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titleText;
        public TextView yearText;
        public Button btn, movieEdit, movieDelete, movieAddtoEditEvent;
        public ImageView img;
        //public Button btn2;


        public MyViewHolder(View v){
            super(v);
            titleText= v.findViewById(R.id.myTextView2);
            yearText =  v.findViewById(R.id.myTextView3);
            btn = v.findViewById(R.id.button_movie_add);
            movieEdit = v.findViewById(R.id.buttonEditRecyclerMovie);
            movieDelete = v.findViewById(R.id.buttonDeleteRecyclerMovie);
            movieAddtoEditEvent = v.findViewById(R.id.buttonAddtoEvent);
            img = v.findViewById(R.id.image);


           // btn2 = v.findViewById(R.id.button2);



            //btn.setOnClickListener(new DeleteMovieListener(getAdapterPosition() + 1, MovieAdapter.this));





        }




    }


}
