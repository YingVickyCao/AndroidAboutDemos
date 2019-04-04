package com.example.hades.androidpo._1_render_op.layout._reduce_hierachy;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hades.androidpo.R;

public class ReduceHierarchyFragment extends Fragment {

    public static ReduceHierarchyFragment newInstance() {
        return new ReduceHierarchyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reduce_hierarchy_layout, container, false);
    }
}