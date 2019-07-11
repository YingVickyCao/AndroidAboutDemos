package com.hades.android.example.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView img = findViewById(R.id.img);
        ImageView img2 = findViewById(R.id.img2);

        Picasso.with(this).load("https://www.baidu.com/img/baidu_resultlogo@2.png").placeholder(R.drawable.ic_launcher_background).into(img);
        Picasso.with(this).load("https://www.gstatic.cn/devrel-devsite/va3a0eb1ff00a004a87e2f93101f27917d794beecfd23556fc6d8627bba2ff3cf/android/images/lockup.svg")
                .placeholder(R.drawable.ic_launcher_background)
                .into(img2);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setSelected(!img.isSelected());
            }
        });

    }
}