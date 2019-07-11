package com.hades.example.android.other_ui._navigation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hades.example.android.R;

public class TestBottomNavigationActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_ui_navigation_bottomnavigationview);

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        int[] titles = {R.string.home, R.string.dashboard, R.string.notifications};
//        int[] icons = {R.drawable.drawable_vector_home, R.drawable.drawable_vector_dashboard, R.drawable.drawable_vector_notifications};
//
//        for (int i = 0; i < 3; i++) {
//            int id = View.generateViewId();
//            menu.add(i, id, i, titles[i]);
//            MenuItem item = menu.findItem(id);
//            item.setIcon(icons[i]);
//        }
//
//        return super.onCreateOptionsMenu(menu);
//    }
}
