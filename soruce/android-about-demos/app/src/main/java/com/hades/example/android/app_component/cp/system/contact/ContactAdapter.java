package com.hades.example.android.app_component.cp.system.contact;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    final ArrayList<ContactInfo> mData;
    private Context mContext;

    public ContactAdapter(ArrayList<ContactInfo> data, Context context) {
        mData = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public ContactInfo getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        ContactInfo contactInfo = getItem(position);

        TextView name = getTextView();
        name.setText(contactInfo.getName());
        linearLayout.addView(name);

        if (contactInfo.getPhones() != null) {
            for (int i = 0; i < contactInfo.getPhones().size(); i++) {
                String phone = contactInfo.getPhones().get(i);
                TextView phoneView = getTextView();
                phoneView.setText(phone);
                linearLayout.addView(phoneView);
            }
        }

        if (contactInfo.getEmails() != null) {
            for (int i = 0; i < contactInfo.getEmails().size(); i++) {
                String email = contactInfo.getEmails().get(i);
                TextView emailView = getTextView();
                emailView.setText(email);
                linearLayout.addView(emailView);
            }
        }

        return linearLayout;
    }

    private TextView getTextView() {
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
        TextView textView = new TextView(mContext);
        textView.setLayoutParams(lp);
        textView.setTextSize(20);
        return textView;
    }
}
