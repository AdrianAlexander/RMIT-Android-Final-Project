package com.example.testing.model.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.example.testing.model.Event;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.Movie;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class EventViewModel extends  AndroidViewModel implements PropertyChangeListener {

    private String TAG = getClass().getName();
    private Model model;
    private String startDate;
    private String endDate;

    private MutableLiveData<ArrayList<Event>> itemsLiveData;
    //private MutableLiveData<ArrayList<Movie>> moviesLiveData;

    public EventViewModel(Application application){
        super(application);
        model = ModelImpl.getSingletonInstance(getApplication());

        model.addPropertyChangeListener(EventViewModel.this);
    }

    public LiveData<ArrayList<Event>> getItems() {
        if(itemsLiveData == null) {
            itemsLiveData = new MutableLiveData<>();
            itemsLiveData.setValue(model.getEventList());
            Log.i(TAG, "getItems: ");
        }
        return itemsLiveData;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName() == "delete event"){
            itemsLiveData.setValue(model.getEventList());
        }else if(evt.getPropertyName() == "edit event"){
            itemsLiveData.setValue(model.getEventList());
        }else if(evt.getPropertyName() == "add event"){
            itemsLiveData.setValue(model.getEventList());
        }
    }

}
