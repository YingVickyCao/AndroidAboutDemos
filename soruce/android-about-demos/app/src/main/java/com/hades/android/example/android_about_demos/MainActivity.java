package com.hades.android.example.android_about_demos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hades.android.example.android_about_demos.nested_scroll_view.NestedScrollViewHasRecycleViewFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        new TestAppend().test();
//                    }
//                }).start();
//            }
//        });

        /*getFragmentManager().beginTransaction()
                .replace(R.id.root, getTestFragment(), getTestFragmentTag())
                .commit();*/
    }

//    private Fragment getTestFragment() {
//        return ScrollViewHasListViewFragment.newInstance();
//        return NestedScrollViewHasRecycleViewFragment.newInstance();
//        return ScrollViewAboveListViewFragment.newInstance();
//    }


    private String getTestFragmentTag() {
//        return ScrollViewHasListViewFragment.class.getSimpleName();
        return NestedScrollViewHasRecycleViewFragment.class.getSimpleName();
//        return ScrollViewAboveListViewFragment.class.getSimpleName();
    }

}
