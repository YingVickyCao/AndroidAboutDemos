package com.hades.example.android.app_component._activity._children;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class TestLauncherActivity extends LauncherActivity {

    String[] activityNames = {"Set app parameters", "ExpandableListActivity"};

    Class<?>[] activityClasses = {TestPreferenceActivity.class, TestExpandableListActivity.class};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activityNames);
        setListAdapter(adapter);
    }

    @Override
    public Intent intentForPosition(int position) {
        return new Intent(TestLauncherActivity.this, activityClasses[position]);
    }
}
