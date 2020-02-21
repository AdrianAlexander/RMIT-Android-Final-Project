package com.example.testing.model.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.Movie;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieViewModel extends AndroidViewModel implements PropertyChangeListener {
    private String TAG = getClass().getName();
    private Model model;

    private MutableLiveData<ArrayList<Movie>> itemsLiveData;

    public MovieViewModel(Application application) {
        super(application);
        model = ModelImpl.getSingletonInstance(getApplication());

        model.addPropertyChangeListener(MovieViewModel.this);
    }

    public LiveData<ArrayList<Movie>> getItems() {
        if(itemsLiveData == null) {
            itemsLiveData = new MutableLiveData<>();
            itemsLiveData.setValue(model.getItemList());
            Log.i(TAG, "getItems: ");
        }
        return itemsLiveData;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == "update movie"){
            itemsLiveData.setValue(model.getItemList());
        }else if(evt.getPropertyName() == "delete movie"){
            itemsLiveData.setValue(model.getItemList());
        }else if(evt.getPropertyName() == "add movie"){
            itemsLiveData.setValue(model.getItemList());
        }
    }
}


