package com.hades.example.android.app_component._fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseActivity;

public class TestFragmentActivity extends BaseActivity {

    View fragmentContainer1;
    View fragmentContainer2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment_container);

//        testHideShow();
        testBackStack();
    }

    private void testHideShow() {
        fragmentContainer1 = findViewById(R.id.fragmentContainer1);
        fragmentContainer2 = findViewById(R.id.fragmentContainer2);

        showFragment(new Fragment1(), R.id.fragmentContainer1);
        showFragment(new Fragment2(), R.id.fragmentContainer2);

        findViewById(R.id.showFragment1).setOnClickListener(v -> showFragment1());
        findViewById(R.id.showFragment2).setOnClickListener(v -> showFragment2());

    }

    private void showFragment1() {
        fragmentContainer1.setVisibility(View.VISIBLE);
        fragmentContainer2.setVisibility(View.GONE);
    }

    private void showFragment2() {
        fragmentContainer1.setVisibility(View.GONE);
        fragmentContainer2.setVisibility(View.VISIBLE);
    }

    private void testBackStack() {
        findViewById(R.id.fragmentContainer1).setVisibility(View.VISIBLE);
        findViewById(R.id.replaceBy1).setOnClickListener(v -> replaceBy1());
        findViewById(R.id.replaceBy2).setOnClickListener(v -> replaceBy2());
    }

    private void replaceBy1() {
        replaceBy(new Fragment1());
    }

    private void replaceBy2() {
        replaceBy(new Fragment2());
    }

    /*
     * <pre>
     * 1 replace（）+  addToBackStack("testBackStack")                    => 1 new instance, 重新 onAttach, onCreate ...
     * 2 add() +  addToBackStack("testBackStack"), Click 1 -> 2 -> 1,    => 1 new instance, 重新 onAttach, onCreate ...
     * 3 add() +  addToBackStack("testBackStack"), Click 1 -> 2 -> Back  =>  new instance, 重新 onAttach, onCreate ...
         * // Show 1
         * D/Fragment1: onAttach: 241223300
         * D/Fragment1: onCreate: 24ßß1223300
         * D/Fragment1: onCreateView: 241223300
         * D/Fragment1: onStart: 241223300
         * D/Fragment1: onResume: 241223300
         *
         * // Show 2
         * D/Fragment2: onAttach: 127075433
         * D/Fragment2: onCreate: 127075433
         * D/Fragment2: onCreateView: 127075433
         * D/Fragment2: onStart: 127075433
         * D/Fragment2: onResume: 127075433
         *
         * // Fragmen2t2 Exit, Show 1
         * D/Fragment2: onPause: 127075433
         * D/Fragment2: onStop: 127075433
         * D/Fragment2: onDestroyView: 127075433
         * D/Fragment2: onDestroy: 127075433
         * D/Fragment2: onDetach: 127075433
     * <pre/>
     */
    private void replaceBy(Fragment fragment) {
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer1, fragment, fragment.getClass().getSimpleName()).addToBackStack("testBackStack").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer1, fragment, fragment.getClass().getSimpleName()).addToBackStack("testBackStack").commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer1, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer1, fragment, fragment.getClass().getSimpleName()).commit();
    }
}