package com.example.hades.androidpo.temp._10_enhanced_loop_4_array;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hades.androidpo.R;
import com.example.hades.androidpo.temp._10_enhanced_loop_4_array.type1.Loop1;
import com.example.hades.androidpo.temp._10_enhanced_loop_4_array.type2.Loop2;
import com.example.hades.androidpo.temp._10_enhanced_loop_4_array.type3.Loop3;

public class ArrayLoopFragment extends Fragment {

    public final static int ARRAY_SIZE = 100000;

    public static ArrayLoopFragment newInstance() {
        return new ArrayLoopFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_array_loop_layout, container, false);
        view.findViewById(R.id.array_loop_1).setOnClickListener(v -> btn1Click());
        view.findViewById(R.id.array_loop_2).setOnClickListener(v -> btn2Click());
        view.findViewById(R.id.array_loop_3).setOnClickListener(v -> btn3Click());
        view.findViewById(R.id.array_loop_4).setOnClickListener(v -> btn4Click());
        view.findViewById(R.id.array_loop_5).setOnClickListener(v -> btn5Click());
        view.findViewById(R.id.array_loop_6).setOnClickListener(v -> btn6Click());
        return view;
    }

    private void btn1Click() {
        new Thread(() -> new Loop1().zero()).start();
    }

    private void btn2Click() {
        new Thread(() -> new Loop1().two()).start();
    }

    private void btn3Click() {
        new Thread(() -> new Loop2().zero()).start();
    }

    private void btn4Click() {
        new Thread(() -> new Loop2().two()).start();
    }

    private void btn5Click() {
        new Thread(() -> new Loop3().zero()).start();
    }

    private void btn6Click() {
        new Thread(() -> new Loop3().two()).start();
    }
}
