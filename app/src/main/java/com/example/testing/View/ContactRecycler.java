package com.example.testing.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.testing.Adapter.ContactAdapter;
import com.example.testing.Adapter.EventAdapter;
import com.example.testing.R;
import com.example.testing.model.Contact;
import com.example.testing.model.ContactImpl;
import com.example.testing.model.Event;
import com.example.testing.model.Model;
import com.example.testing.model.ModelImpl;
import com.example.testing.model.ViewModel.ContactViewModel;
import com.example.testing.model.ViewModel.EventViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class ContactRecycler extends AppCompatActivity {

    private String TAG = getClass().getName();
    private Model model;
    ArrayList<Contact> myDataset;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_recycler);
        model = ModelImpl.getSingletonInstance(this);
        getAllContacts();

        mRecyclerView = (RecyclerView)findViewById(R.id.contactRecycler);
        Model model = ModelImpl.getSingletonInstance(ContactRecycler.this);
        myDataset = model.getContactList();





       ContactViewModel contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);//refer ke view model

        contactViewModel.getItems().observe(this, new Observer<ArrayList<Contact>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Contact> contacts) {
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(ContactRecycler.this);
                mAdapter = new ContactAdapter(getApplicationContext(), contacts);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter );
            }
        });

    }

    public void getAllContacts(){

        Contact contact;
        ArrayList <Contact>contactVoList = new ArrayList();

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    contact = new ContactImpl();
                    contact.setContactName(name);

                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null);

                    Log.i(TAG, id + " = " + name);
                    while (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contact.setContactNumber(phoneNumber);
                    }

                    phoneCursor.close();
                    contactVoList.add(contact);
                    model = ModelImpl.getSingletonInstance(this);
                    model.addContacttoArrayList(contactVoList);
                    //contacts.add(contact);1
                    Log.i(TAG, contactVoList.get(0).toString());
                    ;
                    /*Cursor emailCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (emailCursor.moveToNext()) {
                        String emailId = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    }*/


                }

            }
        }
    }

    /*
    public void getAllContacts(){
        ArrayList<Contact> contactVOList = new ArrayList();
        Contact contact;

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    contact = new ContactImpl();
                    contact.setContactName(name);

                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null);
                    if (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contact.setContactNumber(phoneNumber);
                    }

                    phoneCursor.close();

                    Cursor emailCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (emailCursor.moveToNext()) {
                        String emailId = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    }
                    contactVOList.add(contact);
                    //contactVOList.add(contactVO);
                    //contacts.add(contact);1
                    //model = ModelImpl.getSingletonInstance(this);
                    //model.addContacttoArrayList(contact);
                }
            }
            ContactAdapter adapt = new ContactAdapter(getApplicationContext(), contactVOList);
            mLayoutManager = new LinearLayoutManager(ContactRecycler.this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapt);
        }
    }
*/



}
