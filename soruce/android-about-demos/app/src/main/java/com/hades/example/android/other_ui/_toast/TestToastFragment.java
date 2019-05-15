package com.hades.example.android.other_ui._toast;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

/*
  https://www.jianshu.com/p/ca8d7dd6172e
 */
public class TestToastFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_ui_toast, container, false);
        view.findViewById(R.id.showNormalToast).setOnClickListener(v -> toast());
        view.findViewById(R.id.androidToast).setOnClickListener(v -> androidToast());
        view.findViewById(R.id.showCustomToast).setOnClickListener(v -> showCustomToast());
        view.findViewById(R.id.devCustom).setOnClickListener(v -> devCustom());
        view.findViewById(R.id.lightToast).setOnClickListener(v -> lightToast());
        return view;
    }

    private Context getUsedContext() {
        return getActivity();
    }

    private void toast() {
        Toast.makeText(getUsedContext(), "简单的提示信息", Toast.LENGTH_SHORT).show();
    }

    private void androidToast() {
        Toast toast = new Toast(getUsedContext());
//        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(LayoutInflater.from(getUsedContext()).inflate(R.layout.transient_no1tification_v1, null));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    private void showCustomToast() {
        Toast toast = new Toast(getUsedContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(getToastView());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    private View getToastView() {
        ImageView image = new ImageView(getUsedContext());
        image.setImageResource(R.drawable.ic_launcher);

        LinearLayout ll = new LinearLayout(getUsedContext());
        ll.addView(image);
        TextView textView = new TextView(getUsedContext());
        textView.setText("带图片的提示信息");
        textView.setTextSize(24);
        textView.setTextColor(Color.MAGENTA);
        ll.addView(textView);

        return ll;
    }

    private void devCustom() {
        Toast toast = new Toast(getUsedContext());
        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.setView(LayoutInflater.from(getUsedContext()).inflate(R.layout.other_ui_toast_custom_dev, null));
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    private void lightToast() {
        Toast toast = new Toast(getUsedContext());
        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.setView(LayoutInflater.from(getUsedContext()).inflate(R.layout.other_ui_toast_custom_light, null));
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
