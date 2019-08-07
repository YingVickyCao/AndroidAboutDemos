package com.hades.example.android.widget._layout.linearlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

public class TestLinearLayoutCannotChangeColorActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.widget_layout_linearlayout_4_can_not_change_color);

        addView(null);

        findViewById(R.id.btn1).setOnClickListener(v -> btn1());
        findViewById(R.id.btn2).setOnClickListener(v -> btn2());
        findViewById(R.id.btn3).setOnClickListener(v -> btn3());
    }

    private void btn1() {
        btn(0);
    }

    private void btn2() {
        btn(1);
    }

    private void btn3() {
        btn(2);
    }

    private void btn(int index) {
        LinearLayout ll = findViewById(R.id.linearLayoutContainer);
        if (null == ll) {
            return;
        }
        View view = ll.getChildAt(index);

        ImageView img = view.findViewById(R.id.img);
        img.setSelected(!img.isSelected());

        TextView text2 = view.findViewById(R.id.text2);
        text2.setSelected(!text2.isSelected());
    }

    private void addView(View view) {

        LinearLayout ll = findViewById(R.id.linearLayoutContainer);
        if (null == ll) {
            return;
        }
        int count = 3;
        int[] icon = new int[]{R.drawable.wait_icon, R.drawable.info_icon, R.drawable.ic_launcher};
        String[] textStr1 = new String[]{"A", "B", "C"};
        String[] textStr2 = new String[]{"1", "2", "3"};

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.size_200));
        ll.setLayoutParams(layoutParams);
        ll.setWeightSum(count);


        for (int i = 0; i < count; i++) {
            LinearLayout item = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.widget_layout_linearlayout_4_can_not_change_color_4_item, null);
            item.setOnClickListener(v -> {
                ImageView img = item.findViewById(R.id.img);
                img.setSelected(!img.isSelected());
                TextView text2 = item.findViewById(R.id.text2);
                text2.setSelected(!text2.isSelected());
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            item.setLayoutParams(params);

            ImageView img = item.findViewById(R.id.img);
            img.setImageResource(icon[i]);

            TextView text1 = item.findViewById(R.id.text1);
            text1.setText(textStr1[i]);

            TextView text2 = item.findViewById(R.id.text2);
            text2.setText(textStr2[i]);

            ll.addView(item);
        }
    }
}