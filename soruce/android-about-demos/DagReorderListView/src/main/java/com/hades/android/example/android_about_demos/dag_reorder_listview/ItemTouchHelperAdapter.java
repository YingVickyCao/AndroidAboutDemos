package com.hades.android.example.android_about_demos.dag_reorder_listview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.MyViewHolder> implements IItemTouchHelperAdapter {
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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_recyclerview_drag_reorder_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Message msg = list.get(position);
        holder.info.setText(msg.getInfo());
        holder.icon.setOnClickListener(v -> Toast.makeText(v.getContext(), "Click " + msg.getId(), Toast.LENGTH_SHORT).show());

        holder.drag.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (null != mStartDragListener) {
                    mStartDragListener.startDrag(holder);
                }
            }
            return false;
        });
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

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private View root;
        private TextView info;
        private Button drag;
        private ImageView icon;

        MyViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            info = itemView.findViewById(R.id.info);
            drag = itemView.findViewById(R.id.drag);
            icon = itemView.findViewById(R.id.icon);
        }
    }

}