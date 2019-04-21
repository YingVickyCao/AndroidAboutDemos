package com.example.hades.android.gradleconfigcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.hades.calculator.Calculator;
import com.example.hades.calculator.SumActivity;
import com.example.hades.calculator2.Calculator2;
import com.example.hades.calculator2.SumActivity2;
import com.example.hades.calculator3.Calculator3;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.testBtn).setOnClickListener(v -> testBtn());
        findViewById(R.id.testBtn2).setOnClickListener(v -> testBtn2());
        findViewById(R.id.testBtn3).setOnClickListener(v -> testBtn3());
    }

    private void testBtn() {
        Log.d("MainActivity", "testBtn: ");
        Calculator calculator = new Calculator();
        int sum = calculator.sum(10, 20);
        Toast.makeText(this, "sum=" + sum, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, SumActivity.class));
    }

    private void testBtn2() {
        Calculator2 calculator = new Calculator2();
        int sum = calculator.sum(10, 20);
        Toast.makeText(this, "sum=" + sum, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, SumActivity2.class));
    }

    private void testBtn3() {
        Calculator3 calculator = new Calculator3();
        int sum = calculator.sum(10, 50);
        Toast.makeText(this, "sum=" + sum, Toast.LENGTH_SHORT).show();
    }
}