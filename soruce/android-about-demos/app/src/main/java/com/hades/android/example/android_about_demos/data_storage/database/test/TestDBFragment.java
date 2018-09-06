package com.hades.android.example.android_about_demos.data_storage.database.test;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.data_storage.database.bean.ContactInfo;

import java.util.List;

public class TestDBFragment extends Fragment {

    private List<ContactInfo> mData;
    private ListView listView;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_db_layout, container, false);

        view.findViewById(R.id.read).setOnClickListener(arg0 -> read());
        view.findViewById(R.id.write).setOnClickListener(arg0 -> write());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ContactsManager.programStart(getActivity().getApplication());
        read();

        listView = (ListView) view.findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(getActivity());
        listView.setAdapter(adapter);
    }

    private void read() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mData = ContactsManager.getInstance().readContactInfo();
            }
        }).start();
    }

    private void write() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mData = ContactsManager.getInstance().insertContactInfos();
            }
        }).start();
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;// 动态布局映射

        private class ItemHolder {
            TextView id;
            TextView name;
            TextView phone;
        }

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        // 决定ListView有几行可见
        @Override
        public int getCount() {
            if (null == mData) {
                return 0;
            }
            return mData.size();// ListView的条目数
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ItemHolder itemHolder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_view, null);//根据布局文件实例化view
                itemHolder = new ItemHolder();
                itemHolder.id = convertView.findViewById(R.id.id);
                itemHolder.name = convertView.findViewById(R.id.name);
                itemHolder.phone = convertView.findViewById(R.id.phone);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder) convertView.getTag();
            }
            itemHolder.phone.setText(String.valueOf(mData.get(position).getId()));
            itemHolder.name.setText(mData.get(position).getPhone());//给该控件设置数据(数据从集合类中来)
            itemHolder.id.setText(mData.get(position).getName());//给该控件设置数据(数据从集合类中来)
            return convertView;
        }
    }

}
