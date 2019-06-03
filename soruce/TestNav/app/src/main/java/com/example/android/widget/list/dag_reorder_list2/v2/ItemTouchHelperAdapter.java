package com.example.android.widget.list.dag_reorder_list2.v2;

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

    List<Message> list;
    private StartDragListener mStartDragListener;

    private int groupResId;
    List<Integer> expandPositionList = new ArrayList<>();

    public ItemTouchHelperAdapter(List<Message> list) {
        this.list = list;
    }

    public void setStartDragListener(StartDragListener startDragListener) {
        this.mStartDragListener = startDragListener;
    }

    public void setGroupResId(int resId) {
        groupResId = resId;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_recyclerview_4_drag_reorder_item_view_v2_1, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(groupResId, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        Log.d(TAG, "onCreateViewHolder: @ItemViewHolder=" + itemViewHolder.hashCode());
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        Message bean = list.get(position);
        holder.info.setText(bean.getInfo());
        holder.drag.setOnLongClickListener(v -> {
            if (isHasExpand()) {
                collapse();
            } else {
                expandPositionList.clear();
                startDrag(holder);
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
        return list.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    // PO: 2019-06-03
    private void toggleExpand(View view, final Message bean, int position) {
        Log.d(TAG, "toggleExpand:start" + expandPositionList.toString());
        bean.setExpand(!bean.isExpand());
//        notifyDataSetChanged();
        if (bean.isExpand()) {
            expandPositionList.add(position);
        } else {
            int index = expandPositionList.indexOf(position);
            if (-1 != index && index <= (expandPositionList.size() - 1)) {
                expandPositionList.remove(index);
            }
        }
        Log.d(TAG, "toggleExpand:end" + expandPositionList.toString());

        view.setVisibility(bean.isExpand() ? View.VISIBLE : View.GONE);
    }

    private boolean isHasExpand() {
        return !expandPositionList.isEmpty();
    }

    private void collapse() {
        Log.d(TAG, "collapse:start" + expandPositionList.toString());
        for (Integer pos : expandPositionList) {
            if (null != pos && pos > 0 && list != null && pos <= (list.size() - 1)) {
                Message message = list.get(pos);
                if (null != message) {
                    message.setExpand(false);
                }
            }
        }
        if (null != expandPositionList) {
            expandPositionList.clear();
        }
        notifyDataSetChanged();
        Log.d(TAG, "collapse:end" + expandPositionList.toString());
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder implements IItemTouchHelperViewHolder {
        private View root;
        private TextView info;
        private Button drag;
        private ViewGroup childContainer;
        private ViewGroup groupContainer;

        ItemViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            info = itemView.findViewById(R.id.phone);
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