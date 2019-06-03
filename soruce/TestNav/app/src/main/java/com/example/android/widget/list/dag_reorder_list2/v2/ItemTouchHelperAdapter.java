package com.example.android.widget.list.dag_reorder_list2.v2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.widget.list.dag_reorder_list2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.ItemViewHolder> implements IItemTouchHelperAdapter {
    private static final String TAG = ItemTouchHelperAdapter.class.getSimpleName();

    List<Message> mList;
    private StartDragListener mStartDragListener;
    private int mGroupResId;
    private Activity mContext;
    private List<Integer> mExpandPositionList = new ArrayList<>();

    public ItemTouchHelperAdapter(List<Message> mList, Activity context) {
        this.mList = mList;
        this.mContext = context;
    }

    public void setStartDragListener(StartDragListener startDragListener) {
        this.mStartDragListener = startDragListener;
    }

    public void setGroupResId(int resId) {
        mGroupResId = resId;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_recyclerview_4_drag_reorder_item_view_v2_1, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(mGroupResId, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        Log.d(TAG, "onCreateViewHolder: @ItemViewHolder=" + itemViewHolder.hashCode());
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        Message bean = mList.get(position);
        holder.groupTitle.setText(bean.getInfo());
        holder.drag.setOnLongClickListener(v -> {
            if (null != mStartDragListener) {
                mStartDragListener.showLoading();
            }
            if (isHasExpand()) {
                new Thread(() -> collapse()).start();
            } else {
                new Thread(() -> {
                    mExpandPositionList.clear();
                    startDrag(holder);
                }).start();
            }
            return true;
        });

        List<Child> children = bean.getChildren();
        int preChildCount = holder.childContainer.getChildCount();
        if (children == null || children.isEmpty() || children.size() != holder.childContainer.getChildCount()) {
            holder.childContainer.removeAllViews();
        }

        if (null != children && !children.isEmpty() && children.size() != holder.childContainer.getChildCount()) {
            for (int i = 0; i < children.size(); i++) {
                TextView textView = (TextView) LayoutInflater.from(holder.drag.getContext()).inflate(android.R.layout.simple_list_item_activated_1, null);
                textView.setBackgroundColor(Color.WHITE);
                String childText = String.valueOf(children.get(i).childText);
                textView.setText(childText);
                textView.setOnClickListener(v -> openPage(v.getContext(), childText));
                holder.childContainer.addView(textView);
            }
        }

        if (holder.childContainer.getChildCount() > 0) {
            holder.groupContainer.setOnClickListener(v -> toggleExpand(holder.childContainer, bean, position));
        } else {
            holder.groupContainer.setOnClickListener(v -> openPage(v.getContext(), bean.getInfo()));
        }

        Log.d(TAG, "onBindViewHolder: position=" + position + ",isExpand=" + bean.isExpand() + ",@ItemViewHolder=" + holder.hashCode() + ",ChildCount=" + preChildCount + "->" + holder.childContainer.getChildCount());

        boolean isExpand = holder.childContainer.getChildCount() > 0 && bean.isExpand();
        holder.childContainer.setVisibility(isExpand ? View.VISIBLE : View.GONE);
    }

    private void openPage(Context context, String text) {
        Toast.makeText(context, "Open " + text, Toast.LENGTH_SHORT).show();
    }

    private void startDrag(ItemViewHolder holder) {
        mStartDragListener.startDrag(holder);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    // PO: 2019-06-03
    private void toggleExpand(View view, final Message bean, int position) {
        Log.d(TAG, "toggleExpand:start" + mExpandPositionList.toString());
        if (null != mStartDragListener) {
            mStartDragListener.showLoading();
        }

        new Thread(() -> {
            bean.setExpand(!bean.isExpand());
//        notifyDataSetChanged();
            if (bean.isExpand()) {
                mExpandPositionList.add(position);
            } else {
                int index = mExpandPositionList.indexOf(position);
                if (-1 != index && index <= (mExpandPositionList.size() - 1)) {
                    mExpandPositionList.remove(index);
                }
            }
            Log.d(TAG, "toggleExpand:end" + mExpandPositionList.toString());

            mContext.runOnUiThread(() -> {
                view.setVisibility(bean.isExpand() ? View.VISIBLE : View.GONE);
                if (null != mStartDragListener) {
                    mStartDragListener.hideLoading();
                }
            });

        }).start();
    }

    private boolean isHasExpand() {
        return !mExpandPositionList.isEmpty();
    }

    private void collapse() {
        Log.d(TAG, "collapse:start" + mExpandPositionList.toString());
        for (Integer pos : mExpandPositionList) {
            if (null != pos && pos > 0 && mList != null && pos <= (mList.size() - 1)) {
                Message message = mList.get(pos);
                if (null != message) {
                    message.setExpand(false);
                }
            }
        }
        if (null != mExpandPositionList) {
            mExpandPositionList.clear();
        }

        mContext.runOnUiThread(() -> {
            notifyDataSetChanged();
            if (null != mStartDragListener) {
                mStartDragListener.hideLoading();
            }
        });
        Log.d(TAG, "collapse:end" + mExpandPositionList.toString());
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder implements IItemTouchHelperViewHolder {
        private View root;
        private TextView groupTitle;
        private Button drag;
        private ViewGroup childContainer;
        private ViewGroup groupContainer;

        ItemViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            groupTitle = itemView.findViewById(R.id.groupTitle);
            drag = itemView.findViewById(R.id.drag);
            childContainer = itemView.findViewById(R.id.childContainer);
            groupContainer = itemView.findViewById(R.id.groupContainer);
        }

        // QA: onItemSelected - show item view bg when drag dragBtn
        @Override
        public void onItemSelected() {
            root.setSelected(true);
        }

        @Override
        public void onItemClear() {
            root.setSelected(false);
        }
    }
}