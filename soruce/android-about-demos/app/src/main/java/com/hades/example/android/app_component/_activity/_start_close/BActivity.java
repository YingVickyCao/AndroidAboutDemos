package com.hades.example.android.app_component._activity._start_close;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

import static com.hades.example.android.app_component._activity._start_close.AActivity.KEY_A;

public class BActivity extends AppCompatActivity {

    public final static String KEY_B = "B";
    public final static int KEY_B_RESULT_CODE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_b);

        findViewById(R.id.pageA).setOnClickListener(v -> pageA());
        findViewById(R.id.jumpThemFinish).setOnClickListener(v -> jumpThemFinish());
        findViewById(R.id.close).setOnClickListener(v -> close());

        findViewById(R.id.returnResult).setOnClickListener(v -> returnResult());

        Intent intent = getIntent();
        long data = intent.getLongExtra(KEY_A, 0);
        runOnUiThread(() -> Toast.makeText(BActivity.this, String.valueOf(data), Toast.LENGTH_SHORT).show());
    }

    /*
     Back Stack:
     A
     B

     ->

     A
     B
     A
     */
    // Depressed:
    private void pageA() {
        Intent intent = new Intent(this, AActivity.class);
        startActivity(intent);
    }

    /*
     Back Stack:
     A
     B

     ->

     A
     A
     */
    // Depressed:
    private void jumpThemFinish() {
        pageA();
        close();
    }

    /*
    Back Stack:
    A
    B

    ->

    A
    */
    // Recommended:
    private void close() {
        finish();
    }

    private void returnResult() {
        Intent intent = getIntent();
        intent.putExtra(KEY_B, 100);
        setResult(KEY_B_RESULT_CODE, intent);

        finish();
//        finishActivity(KEY_A_REQUEST_CODE);
    }
}