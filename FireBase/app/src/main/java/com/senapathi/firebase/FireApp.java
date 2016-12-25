package com.senapathi.firebase;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Senapathi on 25-12-2016.
 */

public class FireApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
