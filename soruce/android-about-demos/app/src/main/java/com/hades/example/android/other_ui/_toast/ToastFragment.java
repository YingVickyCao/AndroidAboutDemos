package com.hades.example.android.other_ui._toast;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hades.example.android.R;

public class ToastFragment extends Fragment {
    public static ToastFragment newInstance() {

        Bundle args = new Bundle();

        ToastFragment fragment = new ToastFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_ui_toast, container, false);
        view.findViewById(R.id.showNormalToast).setOnClickListener(v -> showNormalToast());
        view.findViewById(R.id.showCustomToast).setOnClickListener(v -> showCustomToast());
        view.findViewById(R.id.showCustomToast2).setOnClickListener(v -> showCustomToast2());
        return view;
    }

    private Context getUsedContext() {
        return getActivity();
    }

    private void showNormalToast() {
        Toast.makeText(getUsedContext(), "简单的提示信息", Toast.LENGTH_SHORT).show();
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

    private void showCustomToast2() {
        Toast toast = new Toast(getUsedContext());
        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.setView(getToastView2());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    private View getToastView2() {
        View view = LayoutInflater.from(getUsedContext()).inflate(R.layout.other_ui_toast_custom, null);
        view.findViewById(R.id.customToastBtn).setOnClickListener(v -> Toast.makeText(getUsedContext(), "Click custom Toast btn", Toast.LENGTH_SHORT).show());
        return view;
    }
}
