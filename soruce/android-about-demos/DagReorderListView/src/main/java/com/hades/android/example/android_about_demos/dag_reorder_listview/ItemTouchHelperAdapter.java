package com.hades.android.example.android_about_demos.dag_reorder_listview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.MyViewHolder> implements IItemTouchHelperAdapter {
    private static final String TAG = ItemTouchHelperAdapter.class.getSimpleName();

    List<Message> list;
    private startDragListener startDragListener;

    public ItemTouchHelperAdapter(List<Message> list) {
        this.list = list;
    }

    public void setStartDragListener(ItemTouchHelperAdapter.startDragListener startDragListener) {
        this.startDragListener = startDragListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_recyclerview_drag_reorder_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Message msg = list.get(position);
        holder.tv_username.setText(msg.getUsername());
        holder.iv_icon.setBackgroundResource(msg.getId());
        holder.drag.setOnTouchListener((v, event) -> {
            Log.d(TAG, "onTouch: " + event.getAction());
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                if (null != startDragListener) {
//                    startDragListener.startDrag(holder);
//                    return true;
//                }
//            }
            return false;
        });
        holder.drag.setOnLongClickListener(v -> {
            if (null != startDragListener) {
                startDragListener.startDrag(holder);
                return true;
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
        private TextView tv_username;
        private Button drag;
        private ImageView iv_icon;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            drag = itemView.findViewById(R.id.drag);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }
    }

    public interface startDragListener {
        void startDrag(RecyclerView.ViewHolder viewHolder);

        void startSwipe(RecyclerView.ViewHolder viewHolder);
    }
}