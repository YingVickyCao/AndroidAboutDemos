package com.hades.android.example.foredroid;

import android.app.Application;

import com.sjl.foreground.Foreground;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Foreground.init(this);
    }
}