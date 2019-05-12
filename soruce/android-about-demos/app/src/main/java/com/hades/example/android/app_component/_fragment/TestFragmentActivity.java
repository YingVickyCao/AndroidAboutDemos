package com.hades.example.android.app_component._fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;


public class TestFragmentActivity extends AppCompatActivity implements BookListFragment.Callbacks {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_twopane);
    }

    @Override
    public void onItemSelected(Integer id) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.book_detail_container);
        if (null == fragment) {
            fragment = new BookDetailFragment();

            Bundle arguments = new Bundle();
            arguments.putInt(BookDetailFragment.ITEM_ID, id);
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction().replace(R.id.book_detail_container, fragment).commit();
        } else {
            if (fragment instanceof BookDetailFragment) {
                ((BookDetailFragment) fragment).setBook(BookContent.ITEM_MAP.get(id));
            }

        }
    }
}

