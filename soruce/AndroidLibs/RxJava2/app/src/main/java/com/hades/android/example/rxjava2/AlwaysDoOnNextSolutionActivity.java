package com.hades.android.example.rxjava2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * checkConnection
 */
public class AlwaysDoOnNextSolutionActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = AlwaysDoOnNextSolutionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_always_do_on_next_solution);

        findViewById(R.id.subscribe).setOnClickListener(this);
        findViewById(R.id.connect).setOnClickListener(this);
        findViewById(R.id.disconnect).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subscribe:
                subscribe();
                break;

            case R.id.subscribeInThread:
                subscribeInThread();
                break;

            case R.id.connect:
                connect();
                break;

            case R.id.disconnect:
                disconnect();
                break;
        }
    }

    private void subscribeInThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                subscribe();
            }
        }).start();
    }

    private void subscribe() {
        checkConnection();
    }

    /**
     * 即使多线程中运行checkConnection，每次 subscribe - checkConnection - subject -Disposable，一一对应，而不是乱掉，因为函数运行在 Stack中。
     */
    private void checkConnection() {
        final String subject = String.valueOf(System.currentTimeMillis());
        Log.d(TAG, "checkConnection:================> " + subject);
        getIsConnectSubject()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.put(subject, d);
                        Log.d(TAG, "onSubscribe: cache dispose 4 " + subject);
                    }

                    @Override
                    public void onNext(Boolean isConnect) {
                        Log.d(TAG, "onNext: " + isConnect + ", " + subject);
                        if (isConnect) {
                            Disposable disposable = disposables.get(subject);
                            if (!disposable.isDisposed()) {
                                disposable.dispose();
                                Log.d(TAG, "onNext: dispose 4 " + subject);
                            }
                            subscribeSubject();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
        Log.d(TAG, "checkConnection:<================ " + subject);
    }

    private void subscribeSubject() {
        Log.d(TAG, "Subscribe subject");
    }

    private BehaviorSubject<Boolean> getIsConnectSubject() {
        try {
            int MOCK_DURATION_MINUTE = 5 * 1000;
            Thread.sleep(MOCK_DURATION_MINUTE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mIsConnectSubject;
    }

    private void connect() {
        mIsConnect = true;
        mIsConnectSubject.onNext(true);
    }

    private void disconnect() {
        mIsConnect = false;
        mIsConnectSubject.onNext(false);
    }

    private boolean mIsConnect;
    private BehaviorSubject<Boolean> mIsConnectSubject = BehaviorSubject.create();
    private Map<String, Disposable> disposables = new HashMap<>();
}
