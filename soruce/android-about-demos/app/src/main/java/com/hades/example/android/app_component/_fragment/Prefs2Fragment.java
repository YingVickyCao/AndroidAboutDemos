package com.hades.example.android.app_component._fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.hades.example.android.R;

public class Prefs2Fragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.display_prefs);
        String website = getArguments().getString("website");
        Toast.makeText(getActivity(), "网站域名是：" + website, Toast.LENGTH_LONG).show();
    }
}
