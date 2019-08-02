package com.hades.example.android.app_component._fragment.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

public class BookDetailFragment extends Fragment {
    static final String ITEM_ID = "ITEM_ID";

    private Book book;

    private TextView bookTitle;
    private TextView bookDesc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments() && getArguments().containsKey(ITEM_ID)) {
            book = BookContent.ITEM_MAP.get(getArguments().getInt(ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_detail, container, false);

        bookTitle = rootView.findViewById(R.id.book_title);
        bookDesc = rootView.findViewById(R.id.book_desc);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        update();
    }

    void setBook(Book book) {
        this.book = book;
        update();
    }

    private void update(){
        bookTitle.setText(book.title);
        bookDesc.setText(book.desc);
    }
}
