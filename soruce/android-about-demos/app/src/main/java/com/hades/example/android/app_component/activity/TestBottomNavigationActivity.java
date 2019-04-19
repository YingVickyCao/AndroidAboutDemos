package com.hades.example.android.app_component.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hades.example.android.R;

public class TestBottomNavigationActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.notifications);
                    return true;
            }
            return false;
        });
    }

}
