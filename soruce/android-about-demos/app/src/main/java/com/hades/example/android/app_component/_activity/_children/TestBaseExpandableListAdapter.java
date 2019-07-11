package com.hades.example.android.app_component._activity._children;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hades.example.android.R;

public class TestBaseExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;

    private int[] groupLogos = new int[]{
            R.drawable.drawable_vector_dashboard,
            R.drawable.drawable_vector_notifications,
            R.drawable.drawable_vector_home
    };
    private String[] groupData = new String[]{
            "PC", "Phone", "Animal"
    };

    private String[][] childrenData = new String[][]{
            {"Mac", "IMac", "Dell"},
            {"Apple", "Android", "Nokia"},
            {"Dog", "Cat"}
    };

    TestBaseExpandableListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData[groupPosition];
    }

    @Override
    public int getGroupCount() {
        return groupData.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        /*LinearLayout ll = new LinearLayout(mContext);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ImageView logo = new ImageView(mContext);
        logo.setImageResource(groupLogos[groupPosition]);
        ll.addView(logo);
        TextView textView = getTextView();
        textView.setText(getGroup(groupPosition).toString());
        ll.addView(textView);
        return ll;*/

        convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_expandablelist_group, null);
        ImageView logo = convertView.findViewById(R.id.groupLogo);
        logo.setImageResource(groupLogos[groupPosition]);

        TextView textView = convertView.findViewById(R.id.groupTo);
        textView.setText(getGroup(groupPosition).toString());
        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childrenData[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childrenData[groupPosition].length;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        /*
        TextView textView = getTextView();
        textView.setText(getChild(groupPosition, childPosition).toString());
        return textView;
        */

        convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_expandablelist_child, null);
        TextView textView = convertView.findViewById(R.id.childTo);
        textView.setText(getChild(groupPosition, childPosition).toString());
        return convertView;
    }

    private TextView getTextView() {
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(mContext);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        textView.setPadding((int) mContext.getResources().getDimension(R.dimen.size_5), 0, 0, 0);
        textView.setTextSize(20);
        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}