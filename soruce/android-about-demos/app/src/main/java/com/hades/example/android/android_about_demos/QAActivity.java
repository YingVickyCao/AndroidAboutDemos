package com.hades.example.android.android_about_demos;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class QAActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);

        pageQAAboutView();
    }

    private void pageQAAboutView() {
        QAAboutFragment fragment = new QAAboutFragment();
        getFragmentManager().beginTransaction().replace(R.id.root, fragment, fragment.getClass().getSimpleName()).commit();
    }

}