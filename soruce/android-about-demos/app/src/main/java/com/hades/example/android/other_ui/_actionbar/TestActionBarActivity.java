package com.hades.example.android.other_ui._actionbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class TestActionBarActivity extends NoNeedPermissionActivity {
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.other_ui_action_bar);

        textView = findViewById(R.id.changedTextViewByMenu);
        findViewById(R.id.gradienterView).setOnClickListener(v -> showActionBar());
        findViewById(R.id.hide).setOnClickListener(v -> hideActionBar());
        enableAppIconClick();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.isCheckable()) {
            item.setChecked(true);
        }

        switch (item.getItemId()) {
            case R.id.font_10:
                font_10();
                break;

            case R.id.font_30:
                font_30();
                break;

            case R.id.red:
                red();
                break;


            case R.id.green:
                green();
                break;

            case android.R.id.home:
                home();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void font_10() {
        textView.setTextSize(getResources().getDimension(R.dimen.font_size_5));
    }

    private void font_30() {
        textView.setTextSize(getResources().getDimension(R.dimen.font_size_10));
    }

    private void red() {
        textView.setTextColor(getResources().getColor(android.R.color.holo_red_light));
    }

    private void green() {
        textView.setTextColor(getResources().getColor(android.R.color.holo_green_light));
    }

    private void home(){
        showToast("App Icon click");
    }

    private void enableAppIconClick() {
        ActionBar actionBar = getSupportActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}