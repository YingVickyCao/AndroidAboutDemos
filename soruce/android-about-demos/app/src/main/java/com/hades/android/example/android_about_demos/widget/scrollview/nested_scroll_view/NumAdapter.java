package com.hades.android.example.android_about_demos.widget.scrollview.nested_scroll_view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NumAdapter extends RecyclerView.Adapter<NumAdapter.ViewHolder> {
    private List<String> list;

    public NumAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public NumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mText.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mText;

        ViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(android.R.id.text1);
        }
    }
}
