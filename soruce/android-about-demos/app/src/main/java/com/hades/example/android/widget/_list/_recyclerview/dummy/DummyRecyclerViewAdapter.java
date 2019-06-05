package com.hades.example.android.widget._list._recyclerview.dummy;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hades.example.android.R;
import com.hades.example.android.lib.mock.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link IItemClickAction}.
 * TODO: Replace the implementation with code for your data type.
 */
    public class DummyRecyclerViewAdapter extends RecyclerView.Adapter<DummyRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = DummyRecyclerViewAdapter.class.getSimpleName();
    private int mViewHolderCount = 0;

    private final List<DummyItem> mValues;
    private IItemClickAction mListener;

    public DummyRecyclerViewAdapter(List<DummyItem> items, IItemClickAction listener) {
        mValues = items;
        mListener = listener;
    }

    public void setListener(IItemClickAction listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_recyclerview_dummy_item, parent, false);
        mViewHolderCount++;
        Log.d(TAG, "ViewHolder: " + mViewHolderCount);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: position=" + position);

        holder.item = mValues.get(position);
        holder.id.setText(String.valueOf(mValues.get(position).id));
        holder.title.setText(String.valueOf(mValues.get(position).colo2));
        holder.div.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onItemClickListener(holder.item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView id;
        public final TextView title;
        public final View div;

        public DummyItem item;

        public ViewHolder(View view) {
            super(view);
            div = view;
            id = view.findViewById(R.id.id);
            title = view.findViewById(R.id.title);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
