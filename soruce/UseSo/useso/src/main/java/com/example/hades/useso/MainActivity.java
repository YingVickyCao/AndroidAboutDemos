package com.example.hades.useso;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.hades.generateso.NativeLibUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.sample_text);
        tv.setText(NativeLibUtil.stringFromJNI() + ",sum=" + NativeLibUtil.sum(1000, 500));
    }
}
