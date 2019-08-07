package com.hades.example.android.widget._layout.linearlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void addView() {

        int count = 3;
        int[] icon = new int[]{R.drawable.wait_icon, R.drawable.info_icon, R.drawable.ic_launcher};
        String[] textStr1 = new String[]{"A", "B", "C"};
        String[] textStr2 = new String[]{"1", "2", "3"};

        setWeightSum(count);


        for (int i = 0; i < count; i++) {
            LinearLayout item = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.widget_layout_linearlayout_4_can_not_change_color_4_item, null);
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

            addView(item);
        }
    }

    public void btn(int index) {
        View view = getChildAt(index);

        ImageView img = view.findViewById(R.id.img);
        img.setSelected(!img.isSelected());

        TextView text2 = view.findViewById(R.id.text2);
        text2.setSelected(!text2.isSelected());
    }
}