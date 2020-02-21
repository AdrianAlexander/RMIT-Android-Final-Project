package com.example.testing.model.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.testing.model.Contact;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.Movie;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ContactViewModel extends AndroidViewModel{

    private String TAG = getClass().getName();
    private Model model;

    public ContactViewModel(Application application){
        super(application);
        model = ModelImpl.getSingletonInstance(getApplication());
    }

    private MutableLiveData<ArrayList<Contact>> itemsLiveData;


    public LiveData<ArrayList<Contact>> getItems() {
        if(itemsLiveData == null) {
            itemsLiveData = new MutableLiveData<>();
            itemsLiveData.setValue(model.getContactList());
            Log.i(TAG, "getItems: ");
        }
        return itemsLiveData;
    }
}
