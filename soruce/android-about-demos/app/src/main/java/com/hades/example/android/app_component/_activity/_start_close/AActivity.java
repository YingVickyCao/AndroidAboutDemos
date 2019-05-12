package com.hades.example.android.app_component._activity._start_close;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

import static com.hades.example.android.app_component._activity._start_close.BActivity.KEY_B;
import static com.hades.example.android.app_component._activity._start_close.BActivity.KEY_B_RESULT_CODE;

public class AActivity extends AppCompatActivity {
    public final static String KEY_A = "A";
    public final static int KEY_A_REQUEST_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_a);
        findViewById(R.id.pageB).setOnClickListener(v -> pageB());
        findViewById(R.id.pageBForResult).setOnClickListener(v -> pageBForResult());
    }

    private void pageB() {
        Intent intent = new Intent(AActivity.this, BActivity.class);
        intent.putExtra(KEY_A, System.currentTimeMillis());
        startActivity(intent);
    }

    private void pageBForResult() {
        Intent intent = new Intent(AActivity.this, BActivity.class);
        intent.putExtra(KEY_A, System.currentTimeMillis());
        startActivityForResult(intent, KEY_A_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == KEY_A_REQUEST_CODE && resultCode == KEY_B_RESULT_CODE) {
            Bundle data = intent.getExtras();
            if (null == data) {
                return;
            }
            Integer result = data.getInt(KEY_B);
            Toast.makeText(this, String.valueOf(result), Toast.LENGTH_SHORT).show();
        }
    }
}