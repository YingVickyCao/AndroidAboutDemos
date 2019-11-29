package com.hades.android.example.rxjava2._subscribeOn_vs_observeOn;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.hades.android.example.rxjava2.LogHelper;
import com.hades.android.example.rxjava2.R;

import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;
import timber.log.Timber;

public class TestCase1 extends Activity {
    private static final String TAG = TestCase1.class.getSimpleName();

    TextView tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: -->");

        setContentView(R.layout.activity_test_case_1);

        findViewById(R.id.subscribeOn_vs_observeOn).setOnClickListener(view -> test1_subscribeOn_vs_observeOn());
//        findViewById(R.id.init).setOnClickListener(view -> init());
        findViewById(R.id.test2_send_empty).setOnClickListener(view -> test2_send_empty());

        tip = findViewById(R.id.tip);
        init();
        Log.d(TAG, "onCreate: <--");
    }

    /**
     * <pre>
     *     Observable
     *     .map                    // 操作1   io-1
     *     .flatMap                // 操作2   io-1
     *     .subscribeOn(io)        // set 线程 = io-1
     *     .map                    // 操作3    io-1
     *     .subscribeOn(computation)// 无效，只有第一个subscribeOn有效。
     *     .flatMap                // 操作4    io-1
     *     .observeOn(main)        // set 线程 = main
     *     .map                    // 操作5    main    无效
     *     .flatMap                // 操作6    main    无效
     *     .subscribeOn(io)        // 无效。对 操作5 和 操作6 无效。只有第一个observeOn有效
     *     .subscribe(handleData)            main
     *
     *
     * log:
     * 操作7:subscribe() - onSubscribe thread :main,2
     * 操作1: thread :RxCachedThreadScheduler-2,1095
     * 操作2: thread :RxCachedThreadScheduler-2,1095
     * 操作3: thread :RxCachedThreadScheduler-2,1095
     * 操作4: thread :RxCachedThreadScheduler-2,1095
     * 操作5: thread :main,2
     * 操作6: thread :main,2
     * 操作7:subscribe() - onNext thread :main,2
     *      counter: i=1,thread :main,2
     *      .。.
     *      counter: i=29,thread :main,2
     *
     * 结论：
     * 1 subscribeOn():
     *  1) 影响它的上游、下游
     *  2）位置不重要
     *  3）第一个才有效
     *
     * 2 observeOn():
     *  1) 影响它下游
     *  2）位置重要
     *  3）第一个才有效。一旦调用，线程定。即使调用subscribeOn()转换也无效。
     *
     * <pre/>
     */
    // thread  (result ) ->  main
    private void test1_subscribeOn_vs_observeOn() {
        Observable.just(String.valueOf(30))
                .map(new Function<String, Integer>() { // 操作1
                    @Override
                    public Integer apply(String num) throws Exception {
                        Log.d(TAG, "操作1: " + LogHelper.getThreadInfo());
                        return Integer.valueOf(num) > 10 ? Integer.valueOf(num) : 15;
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<String>>() {// 操作2
                    @Override
                    public ObservableSource<String> apply(Integer s) throws Exception {
                        Log.d(TAG, "操作2: " + LogHelper.getThreadInfo());
                        return Observable.just(String.valueOf(s));
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<String, Integer>() { // 操作3
                         @Override
                         public Integer apply(String num) throws Exception {
                             Log.d(TAG, "操作3: " + LogHelper.getThreadInfo());
                             return Integer.valueOf(num) > 10 ? Integer.valueOf(num) : 15;
                         }
                     }
                )
                .subscribeOn(Schedulers.computation())
                .flatMap(new Function<Integer, ObservableSource<String>>() { //操作4
                    @Override
                    public ObservableSource<String> apply(Integer s) throws Exception {
                        Log.d(TAG, "操作4: " + LogHelper.getThreadInfo());
                        return Observable.just(String.valueOf(s));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, Integer>() { // 操作5
                    @Override
                    public Integer apply(String num) throws Exception {
                        Log.d(TAG, "操作5: " + LogHelper.getThreadInfo());
                        return Integer.valueOf(num) > 10 ? Integer.valueOf(num) : 15;
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<String>>() {//操作6
                    @Override
                    public ObservableSource<String> apply(Integer s) throws Exception {
                        Log.d(TAG, "操作6: " + LogHelper.getThreadInfo());
                        return Observable.just(String.valueOf(s));
                    }
                })
                .subscribeOn(Schedulers.io())        //!!特别注意
                .subscribe(new Observer<String>() { // 操作7
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "操作7:subscribe() - onSubscribe " + LogHelper.getThreadInfo());

                    }

                    @Override
                    public void onNext(String num) {
                        Log.d(TAG, "操作7:subscribe() - onNext " + LogHelper.getThreadInfo());
                        counter(num);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "操作7:subscribe() - onError " + LogHelper.getThreadInfo());

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "操作7:subscribe() - onComplete " + LogHelper.getThreadInfo());
                    }
                });
    }

    private void counter(String num) {
        for (int i = 0; i < Integer.valueOf(num); i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            i++;
            Log.d(TAG, "counter: i=" + i + "," + LogHelper.getThreadInfo());
        }
    }

    /**
     * thread  (void ) ->  main
     * <pre>
     *  subscribe,onSubscribe: thread :main,2
     *
     *  subscribe: thread :RxCachedThreadScheduler-1,2258
     *  accept:    thread :RxCachedThreadScheduler-1,2258
     *
     *  subscribe,onNext: thread :main,2
     * <pre/>
     */
    private void test2_send_empty() {
        Observable.create(new ObservableOnSubscribe<Unit>() {
            @Override
            public void subscribe(ObservableEmitter<Unit> emitter) throws Exception { // thread -1
                Log.d(TAG, "subscribe: " + LogHelper.getThreadInfo());
                emitter.onNext(Unit.INSTANCE);
            }
        }).subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Exception { // thread -1
                        Log.d(TAG, "accept: " + LogHelper.getThreadInfo());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Unit>() {
                    @Override
                    public void onSubscribe(Disposable d) { // main
                        Log.d(TAG, "subscribe,onSubscribe: " + LogHelper.getThreadInfo());
                    }

                    @Override
                    public void onNext(Unit unit) { // main
                        tip.setText("Load finish");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "subscribe,onError: " + LogHelper.getThreadInfo());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "subscribe,onComplete: " + LogHelper.getThreadInfo());
                    }
                });

    }

    private void init() {
        Log.d(TAG, "init: -->");

        /**

         onCreate: -->
         init: -->
         init: <--
         onCreate: <--
         init,accept: thread name=RxCachedThreadScheduler-1,thread id=623
         init,onError: thread name=main,thread id=2,e:Can't create handler inside thread Thread[RxCachedThreadScheduler-1,5,main] that has not called Looper.prepare()
         */

        Observable.create(new ObservableOnSubscribe<Unit>() {
            @Override
            public void subscribe(ObservableEmitter<Unit> emitter) throws Exception { // thread -1
//                Log.d(TAG, "subscribe: " + LogHelper.getThreadInfo());
                emitter.onNext(Unit.INSTANCE);
            }
        }).subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Exception { // thread -1
                        Log.d(TAG, "init,accept: " + LogHelper.getThreadInfo());
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Mock:one Dialog is showd in RxJava2 thread
                        showDialog();
//                        throw new Exception("Exception throwed");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EndlessObserver<Unit>() {
                    @Override
                    public void onNext(Unit unit) { // main
                        Log.d(TAG, "init,onNext: " + LogHelper.getThreadInfo());

                        // Mock : UI is not inited
                        tip.setText("Load finish");
                    }

                    @Override
                    public void onError(Throwable e) { // main
                        Timber.e(e);
                        // ERROR: subscribe,onError: thread name=main,thread id=2,e:Can't create handler inside thread Thread[RxCachedThreadScheduler-1,5,main] that has not called Looper.prepare()
                        Log.e(TAG, "init,onError: " + LogHelper.getThreadInfo() + ",e:" + e.getMessage());
                    }
                });
        Log.d(TAG, "init: <--");
    }

    private void showDialog() {
        new AlertDialog
                .Builder(this)
                .setMessage("Dialog")
                .setPositiveButton(android.R.string.ok, (dialog, which) -> Toast.makeText(TestCase1.this, "Dialog", Toast.LENGTH_SHORT).show())
                .setNeutralButton(android.R.string.cancel, (dialog, which) -> {
                    Log.d(TAG, "onClick: cancel()`");
                    dialog.cancel();
                })
                .create();
    }
}
