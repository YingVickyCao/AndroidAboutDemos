package com.hades.example.android.app_component._activity._children;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.BaseExpandableListAdapter;

import com.hades.example.android.R;

public class TestExpandableListActivity extends ExpandableListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_expandablelist);

        BaseExpandableListAdapter adapter = new TestBaseExpandableListAdapter(this);
        setListAdapter(adapter);
    }
}