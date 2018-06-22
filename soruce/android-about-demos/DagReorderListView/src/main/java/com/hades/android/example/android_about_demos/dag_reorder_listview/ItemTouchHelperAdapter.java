package com.hades.android.example.android_about_demos.dag_reorder_listview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.ItemViewHolder> implements IItemTouchHelperAdapter {
    private static final String TAG = ItemTouchHelperAdapter.class.getSimpleName();

    List<Message> list;
    private StartDragListener mStartDragListener;

    public ItemTouchHelperAdapter(List<Message> list) {
        this.list = list;
    }

    public void setStartDragListener(StartDragListener startDragListener) {
        this.mStartDragListener = startDragListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_recyclerview_drag_reorder_item_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        Message bean = list.get(position);
        holder.info.setText(bean.getInfo());
        holder.check.setImageLevel(bean.isChecked() ? 1 : 0);

//        holder.check.setOnClickListener(v -> Toast.makeText(v.getContext(), "Click " + msg.getId(), Toast.LENGTH_SHORT).show());
//        holder.info.setOnClickListener(v -> Toast.makeText(v.getContext(), "Click " + msg.getId(), Toast.LENGTH_SHORT).show());

        holder.check.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                updateCheckStatus(bean, position);
            }
            // when return back, no background action.
            return false;
        });

//        holder.info.setOnTouchListener((v, event) -> {
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                Toast.makeText(v.getContext(), "[info]Click " + msg.getId(), Toast.LENGTH_SHORT).show();
//            }
//            return false;
//        });

        holder.drag.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (null != mStartDragListener) {
                    mStartDragListener.startDrag(holder);
                }
            }
            return false;
        });

    }

    private void updateCheckStatus(final Message bean, int position) {
        bean.setChecked(!bean.isChecked());
        notifyDataSetChanged();
//        notifyItemChanged(position);
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
        private ImageView check;

        ItemViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            info = itemView.findViewById(R.id.info);
            drag = itemView.findViewById(R.id.drag);
            check = itemView.findViewById(R.id.check);
        }

        //       QA: onItemSelected - show item view bg when drag dragBtn
        @Override
        public void onItemSelected() {
            root.setBackgroundResource(android.R.color.holo_green_dark);
        }

        @Override
        public void onItemClear() {
            root.setBackgroundResource(android.R.color.transparent);
        }
    }

}