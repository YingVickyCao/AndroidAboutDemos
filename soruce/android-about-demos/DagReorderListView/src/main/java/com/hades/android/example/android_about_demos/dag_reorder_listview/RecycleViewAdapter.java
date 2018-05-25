package com.hades.android.example.android_about_demos.dag_reorder_listview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    private static final String TAG = RecycleViewAdapter.class.getSimpleName();

    private List<Message> list;
    private startDragListener startDragListener;

    public RecycleViewAdapter(List<Message> list) {
        this.list = list;
    }

    public void setStartDragListener(RecycleViewAdapter.startDragListener startDragListener) {
        this.startDragListener = startDragListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Message msg = list.get(position);
        holder.tv_username.setText(msg.getUsername());
        holder.tv_time.setText(msg.getTime());
        holder.iv_icon.setBackgroundResource(msg.getImg_id());
//        holder.tv_message.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.d(TAG, "onTouch: " + event.getAction());
//
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (null != startDragListener) {
//                        startDragListener.startDragItem(holder);
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });

        holder.tv_message.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != startDragListener) {
                    startDragListener.startDragItem(holder);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_username, tv_time;
        private Button tv_message;
        private ImageView iv_icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_message = itemView.findViewById(R.id.tv_message);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }
    }

    public interface startDragListener {
        void startDragItem(RecyclerView.ViewHolder viewHolder);
    }
}