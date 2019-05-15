package com.hades.example.android.app_component._intent_and_intent_filter._action_category;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

public class A extends AppCompatActivity {
    private static final String TAG = A.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intent_filter_action_and_category_a);

        findViewById(R.id.action0).setOnClickListener(v -> action0());
        findViewById(R.id.action1).setOnClickListener(v -> action1());
        findViewById(R.id.action2).setOnClickListener(v -> action2());
        findViewById(R.id.action1_action2).setOnClickListener(v -> action1_action2());
        findViewById(R.id.category_2).setOnClickListener(v -> category_2());
        findViewById(R.id.action1_category1_category3).setOnClickListener(v -> cation1_category1_category3());
        findViewById(R.id.action1_category1_category2).setOnClickListener(v -> action1_category1_category2());
        findViewById(R.id.action1_category1_categoryDefault).setOnClickListener(v -> action1_category1_categoryDefault());
        findViewById(R.id.action1_categoryDefault).setOnClickListener(v -> action1_categoryDefault());
        findViewById(R.id.action1_category3).setOnClickListener(v -> action1_category3());
        findViewById(R.id.action1_category2).setOnClickListener(v -> action1_category2());
        findViewById(R.id.action3_categoryDefault).setOnClickListener(v -> action3_categoryDefault());
    }

    private void action0() {
        Intent intent = new Intent();
        intent.setAction("action0");

        // FIXED_ERROR:android.content.ActivityNotFoundException: No Activity found to handle Intent { act=action0 }
//        startActivity(intent);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "ActivityNotFoundException:No Activity found to handle Intent { act=action0 }", Toast.LENGTH_SHORT).show();
        }
    }

    /*
        action：com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.ACTION_1
        Categories：null
     */
    private void action1() {
        Intent intent = new Intent();
        intent.setAction(B.ACTION_1);

        startActivity(intent);
    }

    /*
        action：com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.ACTION_2
        Categories：null
     */
    private void action2() {
        Intent intent = new Intent();
        intent.setAction(B.ACTION_2);

        startActivity(intent);
    }

    /*
        action：com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.ACTION_2
        Categories：null
     */
    private void action1_action2() {
        Intent intent = new Intent();
        intent.setAction(B.ACTION_1);// Override
        intent.setAction(B.ACTION_2);

        startActivity(intent);
    }

    /*
      FIXED_ERROR:android.content.ActivityNotFoundException: No Activity found to handle Intent { cat=[com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.CATEGORY_2] }

      Reason:
        Implicit Intent must have Action. Category 是 对Action的补充说明
     */
    private void category_2() {
        Intent intent = new Intent();
        intent.addCategory(B.CATEGORY_2);
        startActivity(intent);
    }

    /*
        FIXED_ERROR:
        android.content.ActivityNotFoundException: No Activity found to handle Intent {
            act=com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.ACTION_1
            cat=[com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.CATEGORY_2,
                com.hades.example.android.app_component._intent_and_intent_filter._action_category.C.CATEGORY_3]
            }

        Reason:
        Implicit Intent:  ACTION_1 & CATEGORY_2 & CATEGORY_3
     */
    private void cation1_category1_category3() {
        Intent intent = new Intent();
        intent.setAction(B.ACTION_1);
        intent.addCategory(B.CATEGORY_2);
        intent.addCategory(C.CATEGORY_3);

        startActivity(intent);
    }

    /*
        action：com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.ACTION_1
        Categories：{com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.CATEGORY_1,
            com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.CATEGORY_2
            }
     */
    private void action1_category1_category2() {
        Intent intent = new Intent();
        intent.setAction(B.ACTION_1);
        intent.addCategory(B.CATEGORY_1);
        intent.addCategory(B.CATEGORY_2);

        startActivity(intent);
    }

    /*
        action：com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.ACTION_1
        Categories：{android.intent.category.DEFAULT
            , com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.CATEGORY_1}
     */
    private void action1_category1_categoryDefault(){
        Intent intent = new Intent();
        intent.setAction(B.ACTION_1);
        intent.addCategory(B.CATEGORY_1);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        startActivity(intent);
    }

    /*
        action：com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.ACTION_1
        Categories：{android.intent.category.DEFAULT}
     */
    private void action1_categoryDefault() {
        Intent intent = new Intent();
        intent.setAction(B.ACTION_1);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        startActivity(intent);
    }

    /*
        FIXED_ERROR:
        android.content.ActivityNotFoundException: No Activity found to handle Intent {
            act=com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.ACTION_1
            cat=[com.hades.example.android.app_component._intent_and_intent_filter._action_category.C.ACTION_3]
        }
     */
    private void action1_category3() {
        Intent intent = new Intent();
        intent.setAction(B.ACTION_1);
        intent.addCategory(C.ACTION_3);

        startActivity(intent);
    }

    /*
        action：com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.ACTION_1
        Categories：{com.hades.example.android.app_component._intent_and_intent_filter._action_category.B.CATEGORY_2}
     */
    private void action1_category2(){
        Intent intent = new Intent();
        intent.setAction(B.ACTION_1);
        intent.addCategory(B.CATEGORY_2);

        startActivity(intent);
    }

    /*
        FIXED_ERROR:
        android.content.ActivityNotFoundException: No Activity found to handle Intent {
            act=com.hades.example.android.app_component._intent_and_intent_filter._action_category.C.ACTION_3
            cat=[android.intent.category.DEFAULT]
        }
     */
    private void action3_categoryDefault(){
        Intent intent = new Intent();
        intent.setAction(C.ACTION_3);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        startActivity(intent);
    }
}