package com.hades.android.example.rxjava2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * <pre>
 *
 * Mock Question:
 *
 * 业务逻辑：
 * 每次subscribe时，首先检查 If connect， if yes， subscribe。
 * 每次subscribe时，首先检查 If connect， if not， 先 connect，然后再subscribe。
 *
 * 模拟:
 * 每次点击subscribe按钮 = 每次subscribe；
 * 点击connect 按钮 = 状态变为connect；
 * 点击disconnect 按钮 = 状态变为disconnect；
 *
 * 现在的问题：
 * 每次subscribe时，在checkConnection中，只要connect状态变化，就会执行一次 onNext（）。
 * 正确的做法是，onNext（）中的subscribeSubject只执行一次。如果执行过了，即使connect状态变化了，onNext 也不再执行。
 *
 *
 * 情况1：点击subscribe按钮 = 执行一个subscribe。
 * checkConnection,onSubscribe:
 * checkConnection,onNext: false
 * checkConnection,onNext: true
 * Subscribe subject
 * checkConnection,onNext: true
 * Subscribe subject
 *
 * 情况2：点击subscribe按钮多次 = 执行多个subscribe。
 * checkConnection,onSubscribe:
 * checkConnection,onSubscribe:
 * checkConnection,onNext: false
 * checkConnection,onNext: false
 * checkConnection,onNext: false
 * checkConnection,onNext: false
 * checkConnection,onNext: true
 * Subscribe subject
 * checkConnection,onNext: true
 * Subscribe subject
 *
 * 从 log 看出，每次只要connect状态变化了，就会执行 onNext 一次，这是错误的。而且多次 subscribe，状态就乱掉了。
 * 如果有subscribe了 N个 subhect，onNext 就会执行 N * 状态的种类数，这时非常消耗性能的。
 * 即使增加 If isConnect 判断，只有connect 时，才会执行subscribeSubject。
 * 但是除了每一个 subject的第一次执行subscribeSubject，其他次数执行subscribeSubject，都是不需要的。而且每次都需要If isConnect 判断。
 *
 * 最正确的做法：当每个 subject subscribe 时，只要执行subscribeSubject，无论connect状态变化，onNext 也不会执行。
 * <pre/>
 */
public class AlwaysDoOnNextQAActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = AlwaysDoOnNextQAActivity.class.getSimpleName();

    private boolean mIsConnect;
    private BehaviorSubject<Boolean> mIsConnectSubject = BehaviorSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_always_do_on_next);

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

            case R.id.connect:
                connect();
                break;

            case R.id.disconnect:
                disconnect();
                break;
        }
    }

    private void subscribe() {
        checkConnection();
    }

    private void connect() {
        Log.d(TAG, "connect: ");
        mIsConnect = true;
        mIsConnectSubject.onNext(true);
    }

    private void disconnect() {
        Log.d(TAG, "disconnect: ");
        mIsConnect = false;
        mIsConnectSubject.onNext(false);
    }

    private void checkConnection() {
        getIsConnectSubject().subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "checkConnection, onSubscribe: ");
            }

            @Override
            public void onNext(Boolean isConnect) {
                Log.d(TAG, "checkConnection , onNext: " + isConnect);
                if (isConnect) {
                    subscribeSubject();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "checkConnection, onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "checkConnection, onComplete: ");
            }
        });
    }

    private void subscribeSubject() {
        Log.d(TAG, "Subscribe subject");
    }

    private BehaviorSubject<Boolean> getIsConnectSubject() {
        return mIsConnectSubject;
    }
}
