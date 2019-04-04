package com.example.hades.androidpo._5_storage_op.shared_prefs;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hades.androidpo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class TestSharedPreferencesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shared_preferences_layout, container, false);

        view.findViewById(R.id.read).setOnClickListener(arg0 -> read());
        view.findViewById(R.id.write).setOnClickListener(arg0 -> write());
        return view;
    }

    private void read() {
        // 获取只能被本应用程序读、写的SharedPreferences对象
        SharedPreferences preferences = getActivity().getSharedPreferences("PO", MODE_PRIVATE);

        // 读取字符串数据
        String time = preferences.getString("time", null);
        // 读取int类型的数据
        int randNum = preferences.getInt("random", 0);
        String result = time == null ? "您暂时还未写入数据" : "写入时间为：" + time + "\n上次生成的随机数为：" + randNum;
        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
    }

    private void write_PO_before() {
        // 获取只能被本应用程序读、写的SharedPreferences对象
        SharedPreferences preferences = getActivity().getSharedPreferences("PO", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("time", new SimpleDateFormat("YYYY-MM-DD hh:mm:ss").format(new Date()));
        editor.commit();

        SharedPreferences.Editor editor2 = preferences.edit();
        editor2.putInt("random", (int) (Math.random() * 100));
        // Default in main  thread
        editor2.commit();

        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
    }

    private void write() {
        // 获取只能被本应用程序读、写的SharedPreferences对象
        SharedPreferences preferences = getActivity().getSharedPreferences("PO", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("time", new SimpleDateFormat("YYYY-MM-DD hh:mm:ss").format(new Date()));
        editor.putInt("random", (int) (Math.random() * 100));
        // Default in main  thread
        editor.apply();

        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
    }
}