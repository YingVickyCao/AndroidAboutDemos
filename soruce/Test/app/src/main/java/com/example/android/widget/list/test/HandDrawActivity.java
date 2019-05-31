package com.example.android.widget.list.test;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class HandDrawActivity extends AppCompatActivity {
    EmbossMaskFilter emboss;
    BlurMaskFilter blur;
    HandDrawView handDrawView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hand_draw);

        handDrawView = findViewById(R.id.drawView);

        emboss = new EmbossMaskFilter(new float[]{1.5f, 1.5f, 1.5f}, 0.6f, 6, 4.2f);
        blur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
    }

    @Override
    // 负责创建选项菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = new MenuInflater(this);
        // 装载R.menu.my_menu对应的菜单，并添加到menu中
        inflator.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    // 菜单项被单击后的回调方法
    public boolean onOptionsItemSelected(MenuItem mi) {
        // 判断单击的是哪个菜单项，并有针对性地作出响应
        switch (mi.getItemId()) {
            case R.id.red:
                handDrawView.paint.setColor(Color.RED);
                mi.setChecked(true);
                break;
            case R.id.green:
                handDrawView.paint.setColor(Color.GREEN);
                mi.setChecked(true);
                break;
            case R.id.blue:
                handDrawView.paint.setColor(Color.BLUE);
                mi.setChecked(true);
                break;
            case R.id.width_1:
                handDrawView.paint.setStrokeWidth(1);
                break;
            case R.id.width_3:
                handDrawView.paint.setStrokeWidth(3);
                break;
            case R.id.width_5:
                handDrawView.paint.setStrokeWidth(5);
                break;
            case R.id.blur:
                handDrawView.paint.setMaskFilter(blur);
                break;
            case R.id.emboss:
                handDrawView.paint.setMaskFilter(emboss);
                break;
        }
        return true;
    }
}