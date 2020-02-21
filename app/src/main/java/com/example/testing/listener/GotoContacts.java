package com.example.testing.listener;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;

import com.example.testing.View.ContactRecycler;

public class GotoContacts implements View.OnClickListener {

    private Activity act;

    public GotoContacts(Activity act){
        this.act = act;
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        act.startActivityForResult(intent, 2);
    }
}
