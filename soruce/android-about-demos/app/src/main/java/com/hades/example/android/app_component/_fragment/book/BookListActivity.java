package com.hades.example.android.app_component._fragment.book;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;


public class BookListActivity extends AppCompatActivity implements BookListFragment.Callbacks {
    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        if (findViewById(R.id.book_detail_container) != null) {
            mTwoPane = true;
            ((BookListFragment) getSupportFragmentManager().findFragmentById(R.id.book_list)).setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(Integer id) {
        if (mTwoPane) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.book_detail_container);
            if (null == fragment) {
                fragment = new BookDetailFragment();

                Bundle arguments = new Bundle();
                arguments.putInt(BookDetailFragment.ITEM_ID, id);
                fragment.setArguments(arguments);

//                getSupportFragmentManager().beginTransaction().replace(R.id.book_detail_container, fragment).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.book_detail_container, fragment).addToBackStack(BookDetailFragment.class.getSimpleName()).commit();
            } else {
                if (fragment instanceof BookDetailFragment) {
                    ((BookDetailFragment) fragment).setBook(BookContent.ITEM_MAP.get(id));
                }

            }
        } else {
            Intent detailIntent = new Intent(this, BookDetailActivity.class);
            detailIntent.putExtra(BookDetailFragment.ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}