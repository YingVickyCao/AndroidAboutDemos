package com.hades.example.android.app_component._fragment.book;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

public class BookDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putInt(BookDetailFragment.ITEM_ID, getIntent().getIntExtra(BookDetailFragment.ITEM_ID, 0));

            BookDetailFragment fragment = new BookDetailFragment();
            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction().add(R.id.book_detail_container, fragment).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.book_detail_container, fragment).addToBackStack(BookDetailFragment.class.getSimpleName()).commit();
        }
    }
}