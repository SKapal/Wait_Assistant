package com.comp1601.waitassistantv2;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sahilkapal on 2018-02-18.
 */

public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}