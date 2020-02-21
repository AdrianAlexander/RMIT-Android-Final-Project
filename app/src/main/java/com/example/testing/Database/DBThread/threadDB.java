package com.example.testing.Database.DBThread;

import com.example.testing.Database.DB;

public class threadDB implements Runnable {

    private DB dataBase;

    public threadDB(DB database){
        this.dataBase = database;
    }

    @Override
    public void run() {
        try{
            dataBase.deleteTable();
            dataBase.saveDatabase();
        }catch (Exception e){

        }

    }
}
