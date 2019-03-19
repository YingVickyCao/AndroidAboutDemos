package com.hades.example.android.resource.i18n;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hades.example.android.R;

public class InternationalizationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_internationalization, container, false);
        TextView tvShow = view.findViewById(R.id.tableContentList);
        tvShow.setText(R.string.msg);
        return view;
    }
}