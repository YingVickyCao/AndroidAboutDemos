package com.example.hades.togetherdagger1anddagger2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;
import javax.inject.Inject1;

import dagger.ObjectGraph;

public class MainActivity extends Activity {

    TextView txt_dagger1;
    TextView txt_dagger2;
    TextView txt_dagger3;

    @Inject
    B mB;

    @Inject1
    B mB2;

    @Inject1
    @Inject
    B mB3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_dagger1 = findViewById(R.id.txt_dagger1);
        txt_dagger2 = findViewById(R.id.txt_dagger2);
        txt_dagger3 = findViewById(R.id.txt_dagger3);
        findViewById(R.id.test).setOnClickListener(v -> test());
        initialize();
    }

    private void initialize() {
        //Dagger 1
        final ObjectGraph objectGraph = ObjectGraph.create(new Dagger1Module());
        objectGraph.inject(this);

        //Dagger 2
        final Dagger2Component dagger2Component = DaggerDagger2Component.builder()
                .dagger2Module(new Dagger2Module())
                .build();
        dagger2Component.inject(this);
    }


    private void test() {
        txt_dagger1.setText("Class injected with Dagger 1: " + mB.hashCode());
        txt_dagger2.setText("Class injected with Dagger 2: " + mB2.hashCode());
        txt_dagger3.setText("Class injected with Dagger 1+2: " + mB3.hashCode());
    }
}
