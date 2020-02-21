package com.example.testing.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testing.R;
import com.example.testing.model.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Contact> myDatasets;
    public ContactAdapter(Context context, ArrayList<Contact> myDatasets){
        this.context = context;
        this.myDatasets = myDatasets;
    }
    @NonNull
    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contact, parent, false);
        ContactAdapter.MyViewHolder myViewHolder = new ContactAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.MyViewHolder holder, int position) {
        Contact contact = myDatasets.get(position);
        holder.name.setText(myDatasets.get(position).getContactName());
        holder.number.setText(myDatasets.get(position).getContactNumber());

    }

    @Override
    public int getItemCount() {
        return myDatasets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView number;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contactName);
            number = itemView.findViewById(R.id.contactNumber);
            img = itemView.findViewById(R.id.contactImage);
        }
    }
}
