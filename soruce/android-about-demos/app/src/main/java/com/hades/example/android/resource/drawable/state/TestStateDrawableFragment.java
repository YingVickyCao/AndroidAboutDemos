package com.hades.example.android.resource.drawable.state;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

public class TestStateDrawableFragment extends Fragment {
    private ImageView imageview1;
    private ImageView imageview2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_state_list_drawable, container, false);

        imageview1 = view.findViewById(R.id.imageview1);
        imageview2 = view.findViewById(R.id.imageview2);

        view.findViewById(R.id.selectImageView1).setOnClickListener(v -> selectImageView1());
        view.findViewById(R.id.selectImageView2).setOnClickListener(v -> selectImageView2());

        return view;
    }

    private void selectImageView1() {
        selectImageView(imageview1);
    }

    private void selectImageView2() {
        selectImageView(imageview2);
    }

    private void selectImageView(ImageView imageview) {
        imageview.setSelected(!imageview.isSelected());
    }
}