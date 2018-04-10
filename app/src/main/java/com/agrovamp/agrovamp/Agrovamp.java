package com.agrovamp.agrovamp;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Nishat Sayyed on 02-04-2018.
 */

public class Agrovamp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseDatabase.getInstance().getReference().keepSynced(true);
    }
}
