package com.hades.example.android.data_storage.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;
import static com.hades.example.android.Constant.SHARED_PREFERENCES_FILE_NAME;

public class TestSharedPreferencesFragment extends BaseFragment {
    private static final String TAG = TestSharedPreferencesFragment.class.getSimpleName();

    private TextView mContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.io_shared_preferences_layout, container, false);
        mContent = view.findViewById(R.id.content);

        view.findViewById(R.id.read).setOnClickListener(arg0 -> read());
        view.findViewById(R.id.write).setOnClickListener(arg0 -> write());
        view.findViewById(R.id.checkSharedPreferencesIsSameInstance).setOnClickListener(v -> checkSharedPreferencesIsSameInstance());
        view.findViewById(R.id.runnableInMainThread).setOnClickListener(v -> runnableInMainThread());

        return view;
    }

    private void read() {
        // 获取只能被本应用程序读、写的SharedPreferences对象
        SharedPreferences preferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE);

        // 读取字符串数据
        String time = preferences.getString("time", null);
        // 读取int类型的数据
        int randNum = preferences.getInt("random", 0);
        String result = time == null ? "您暂时还未写入数据" : "写入时间为：" + time + "\n上次生成的随机数为：" + randNum;
        mContent.setText(result);
    }

    private void write_PO_before() {
        // 获取只能被本应用程序读、写的SharedPreferences对象
        SharedPreferences preferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("time", new SimpleDateFormat("YYYY-MM-DD hh:mm:ss").format(new Date()));
        editor.commit();

        SharedPreferences.Editor editor2 = preferences.edit();
        editor2.putInt("random", (int) (Math.random() * 100));
        // Default in main  thread
        editor2.commit();
        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
        read();
    }

    private void write() {
        // 获取只能被本应用程序读、写的SharedPreferences对象
        SharedPreferences preferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        Log.d(TAG, "write,Editor hashCode=" + editor.hashCode());
        editor.putString("time", new SimpleDateFormat("YYYY-MM-DD hh:mm:ss").format(new Date()));
        editor.putInt("random", (int) (Math.random() * 100));
        // Default in main  thread
        editor.apply();

        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
        read();
//        PreferenceManager.getDefaultSharedPreferences()
    }

    private void checkSharedPreferencesIsSameInstance() {
        Log.d(TAG, "checkSharedPreferencesIsSameInstance,context=" + getContext().hashCode());
        Log.d(TAG, "checkSharedPreferencesIsSameInstance,Activity context=" + getActivity().hashCode());
        Log.d(TAG, "checkSharedPreferencesIsSameInstance,Application context=" + getActivity().getApplication().hashCode());

        Log.d(TAG, "checkSharedPreferencesIsSameInstance: sf1 START");
        testSharedPreferences("sf1");
        Log.d(TAG, "checkSharedPreferencesIsSameInstance: sf1 END");

        Log.d(TAG, "checkSharedPreferencesIsSameInstance: sf2 START");
        testSharedPreferences("sf2");
        Log.d(TAG, "checkSharedPreferencesIsSameInstance: sf2 END");
    }

    private void testSharedPreferences(String fileName) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Log.d(TAG, "testSharedPreferences,Activity Context=" + getActivity().hashCode() + ",SF hasCode=" + sharedPreferences.hashCode());

        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Log.d(TAG, "testSharedPreferences,Activity Context=" + getActivity().hashCode() + ",SF hasCode=" + sharedPreferences2.hashCode());

        SharedPreferences sharedPreferences3 = getActivity().getApplication().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Log.d(TAG, "testSharedPreferences,Application Context=" + getActivity().getApplication().hashCode() + ",SF hasCode=" + sharedPreferences3.hashCode());

        SharedPreferences sharedPreferences4 = getActivity().getApplication().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Log.d(TAG, "testSharedPreferences,Application Context=" + getActivity().getApplication().hashCode() + ",SF hasCode=" + sharedPreferences4.hashCode());
    }

    private void runnableInMainThread() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                Log.d(TAG, "runnableInMainThread,thread id =" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
            }
        };
        runnable.run();
    }
}