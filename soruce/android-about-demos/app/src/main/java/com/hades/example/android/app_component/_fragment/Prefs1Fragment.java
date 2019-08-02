package com.hades.example.android.app_component._fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.hades.example.android.R;

public class Prefs1Fragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}