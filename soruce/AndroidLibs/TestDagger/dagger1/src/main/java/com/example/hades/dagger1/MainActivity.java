package com.example.hades.dagger1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hades.dagger1.Instance.Multi;
import com.example.hades.dagger1.Instance.Plus;
import com.example.hades.dagger1.Instance.Sum;
import com.example.hades.dagger1.lazy.LazyNum;
import com.example.hades.dagger1.plus.One;
import com.example.hades.dagger1.plus.Two;
import com.example.hades.dagger1.plus.TwoModule;
import com.example.hades.dagger1.singleton.C;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.ObjectGraph;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * 我要new 一个Sum实例
     */
    @Inject
    Sum mSum;

    /**
     * 我要new 一个Plus实例
     */
    @Inject
    Plus mPlus;

    /**
     * 我要new 一个Multi实例
     */
    @Inject
    Multi mMulti;


    /**
     * 我要new 一个C实例
     */
    @Inject
    C mC1;

    /**
     * 我要new 一个C实例
     */
    @Inject
    C mC2;

    /**
     * 我要new 一个One实例
     */
    @Inject
    One mOne1;

    @Inject
    One mOne2;

    @Inject
    Two mTwo;


    /**
     * 我要new 一个LazyNum实例
     */
    @Inject
    Lazy<LazyNum> mLazyNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 我要注入MainActivity中的成员，试着从MainActivityModule中找到如何创建实例
         */
        /**
         * 建图（Building the Graph）
         * @Inject / @Provides标注的类形成了一张通过依赖连接的对象图
         */
        ObjectGraph objectGraph = ObjectGraph.create(new MainActivityModule());
        /**
         * 启动依赖注入
         */
        objectGraph.inject(this);

        /**
         * MainActivityModule 依赖 OneModule
         * One 本来应该在MainActivityModule中找到，但是MainActivityModule中没有，在OneModule中。
         */
        objectGraph.plus(new TwoModule());

        findViewById(R.id.getInstance1).setOnClickListener(v -> getInstance1());
        findViewById(R.id.getInstance2).setOnClickListener(v -> getInstance2());
        findViewById(R.id.getInstance3).setOnClickListener(v -> getInstance3());

        findViewById(R.id.singleton).setOnClickListener(v -> singleton());

        findViewById(R.id.objectGraphPlus).setOnClickListener(v -> objectGraphPlus());

        findViewById(R.id.lazyInjections).setOnClickListener(v -> lazyInjections());
    }

    private void getInstance1() {
        int sum = mSum.sum(10, 20);
        Toast.makeText(this, "sum=" + sum, Toast.LENGTH_SHORT).show();
    }

    private void getInstance2() {
        int plus = mPlus.plus(1000);
        Toast.makeText(this, "plus=" + plus, Toast.LENGTH_SHORT).show();
    }

    private void getInstance3() {
        int multi = mMulti.multi(10, 5);
        Toast.makeText(this, "multi=" + multi, Toast.LENGTH_SHORT).show();
    }

    /**
     * A和B是单例，C每次 inject 都是新的实例
     * C hashCode=: 219271524,A hashCode=96128973,B hashCode=23666818
     * C hashCode=: 137798547,A hashCode=96128973,B hashCode=23666818
     */
    private void singleton() {
        mC1.sayABC();
        mC2.sayABC();
    }

    /**
     * 09-20 23:58:52.650 15974-15974/com.example.hades.dagger1 D/One: One hashCode=205039217
     * 09-20 23:58:52.665 15974-15974/com.example.hades.dagger1 D/One: One hashCode=205039217
     * 09-20 23:58:52.673 15974-15974/com.example.hades.dagger1 D/Two: hashCode=175755716
     */
    private void objectGraphPlus() {
        Toast.makeText(this, mOne1.hi(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, mOne2.hi(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, mTwo.hi(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 我是Lazy 类型，延迟实例化。什么时候用，什么时候实例化。
     * 09-20 23:44:16.746 15280-15280/com.example.hades.dagger1 D/LazyNum: LazyNum:
     * 09-20 23:44:17.349 15280-15280/com.example.hades.dagger1 D/LazyNum: num: 1537458257349,LazyNum hashCode=88345624
     * 09-20 23:44:17.549 15280-15280/com.example.hades.dagger1 D/LazyNum: num: 1537458257549,LazyNum hashCode=88345624
     */
    private void lazyInjections() {
        mLazyNum.get().num();
    }
}