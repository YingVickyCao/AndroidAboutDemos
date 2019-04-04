package com.hades.android.example.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;

    @BindString(R.string.app_name)
    String appName;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (null != unbinder) {
////            unbinder.unbind();
////        }
    }

    @OnClick(R.id.btn1)
    public void btn1Click() {
        Toast.makeText(this, btn1.getText(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn2)
    public void btn2Click() {
        Toast.makeText(this, appName, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn3)
    public void btn3Click() {
        replaceFragment();
    }

    private void replaceFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentRoot, new TestFragmemt(), TestFragmemt.TAG).commit();
    }
}