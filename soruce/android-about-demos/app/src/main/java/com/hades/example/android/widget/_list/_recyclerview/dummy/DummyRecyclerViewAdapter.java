package com.hades.example.android.widget._list._recyclerview.dummy;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private final IItemClickAction mListener;

        mValues = items;
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

        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(mValues.get(position).id));
        holder.mContentView.setText(String.valueOf(mValues.get(position).colo2));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final Button mContentView;
        DummyItem mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
