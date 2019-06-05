package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hades.example.android.R;

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
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_recyclerview_4_drag_reorder_item_view_v2, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_recyclerview_4_drag_reorder_item_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: position=" + position);
        Message bean = list.get(position);
        holder.info.setText(bean.getInfo());
        holder.check.setImageLevel(bean.isChecked() ? 1 : 0);

        holder.root.setOnClickListener(v -> updateCheckStatus(bean));
//        holder.drag.setOnTouchListener((v, event) -> {//drag btn -> Drag row
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                if (null != mStartDragListener) {
//                    mStartDragListener.startDrag(holder);
//                }
//            }
//            return false;
//        });

        holder.drag.setOnLongClickListener(v -> {
            if (null != mStartDragListener) {
                mStartDragListener.startDrag(holder);
            }
            return true;
        });
    }

    private void updateCheckStatus(final Message bean) {
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
        private ImageView check;

        ItemViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            info = itemView.findViewById(R.id.phone);
            drag = itemView.findViewById(R.id.drag);
            check = itemView.findViewById(R.id.check);
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