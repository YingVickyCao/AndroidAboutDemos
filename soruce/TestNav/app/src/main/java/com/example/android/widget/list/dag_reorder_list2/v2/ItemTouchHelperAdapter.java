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

import java.util.Collections;
import java.util.List;

public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.ItemViewHolder> implements IItemTouchHelperAdapter {
    private static final String TAG = ItemTouchHelperAdapter.class.getSimpleName();

    List<Message> list;
    private StartDragListener mStartDragListener;

    private int groupResId;

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
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        Message bean = list.get(position);
        Log.d(TAG, "onBindViewHolder: position=" + position + ",isCollapse=" + bean.isCollapse());
        holder.info.setText(bean.getInfo());
//        holder.check.setImageLevel(bean.isChecked() ? 1 : 0);

//        holder.root.setOnClickListener(v -> updateCheckStatus(bean));
        holder.drag.setOnClickListener(v -> updateCheckStatus(bean));

        holder.drag.setOnLongClickListener(v -> {
            startDrag(holder);
            return true;
        });
        holder.root.setOnLongClickListener(v -> {
            startDrag(holder);
            return true;
        });

        if (bean.isCollapse()) {
            holder.childContainer.setVisibility(View.GONE);
        } else {
            List<Child> children = bean.getChildren();
            if (children.size() >= 1) {
                holder.childContainer.setVisibility(View.VISIBLE);
            } else {
                holder.childContainer.setVisibility(View.GONE);
            }

            if (holder.childContainer.getChildCount() < children.size()) {
                for (int i = 0; i < children.size(); i++) {
                    TextView textView = (TextView) LayoutInflater.from(holder.drag.getContext()).inflate(android.R.layout.simple_list_item_activated_1, null);
                    textView.setBackgroundColor(Color.WHITE);
                    String childText = String.valueOf(children.get(i).childText);
                    textView.setText(childText);
                    textView.setOnClickListener(v -> openPage(v.getContext(), childText));
                    holder.childContainer.addView(textView);
                }
            }
        }
        if (holder.childContainer.getChildCount() > 0) {
            holder.groupContainer.setOnClickListener(v -> updateCheckStatus(bean));
        } else {
            holder.groupContainer.setOnClickListener(v -> openPage(v.getContext(), bean.getInfo()));
        }
    }

    private void openPage(Context context, String text) {
        Toast.makeText(context, "Open " + text, Toast.LENGTH_SHORT).show();
    }

    private void startDrag(ItemViewHolder holder) {
        mStartDragListener.startDrag(holder);
    }

    private void updateCheckStatus(final Message bean) {
        bean.setCollapse(!bean.isCollapse());
        bean.setChecked(!bean.isChecked());
        notifyDataSetChanged();
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

    static class ItemViewHolder extends RecyclerView.ViewHolder implements IItemTouchHelperViewHolder {
        private View root;
        private TextView info;
        private Button drag;
        //        private ImageView check;
        private ViewGroup childContainer;
        private ViewGroup groupContainer;

        ItemViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            info = itemView.findViewById(R.id.phone);
            drag = itemView.findViewById(R.id.drag);
//            check = itemView.findViewById(R.id.check);
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