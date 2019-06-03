package com.example.android.widget.list.dag_reorder_list2.v1;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.widget.list.dag_reorder_list2.R;

import java.util.Collections;
import java.util.List;

import static com.example.android.widget.list.dag_reorder_list2.v1.Item.TYPE_CHILD;
import static com.example.android.widget.list.dag_reorder_list2.v1.Item.TYPE_GROUP;

public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.ItemVH> implements IItemTouchHelperAdapter {
    private static final String TAG = ItemTouchHelperAdapter.class.getSimpleName();

    private StartDragListener mStartDragListener;

    public List<Item> list;

    public ItemTouchHelperAdapter(List<Item> list) {
        this.list = list;
    }

    public void setStartDragListener(StartDragListener startDragListener) {
        this.mStartDragListener = startDragListener;
    }

    @Override
    public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ItemVH itemVH = null;
        switch (viewType) {
            case TYPE_GROUP:
                view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                itemVH = new GroupVH(view);
                break;

            case TYPE_CHILD:
                view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
                itemVH = new ChildVH(view);
                break;
        }

        return itemVH;
    }

//    @Override
//    public void onBindViewHolder(final ItemViewHolder holder, int position) {
//        Log.d(TAG, "onBindViewHolder: position=" + position);
//        Message bean = list.get(position);
//        holder.info.setText(bean.getInfo());
//        holder.check.setImageLevel(bean.isChecked() ? 1 : 0);
//
//        holder.root.setOnClickListener(v -> updateCheckStatus(bean));
//        holder.drag.setOnTouchListener((v, event) -> {//drag btn -> Drag row
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                if (null != mStartDragListener) {
//                    mStartDragListener.startDrag(holder);
//                }
//            }
//            return false;
//        });
//    }

    @Override
    public void onBindViewHolder(ItemVH holder, int position) {
        Item item = list.get(position);
        switch (getItemViewType(position)) {
            case TYPE_GROUP:
                Group g = (Group) item;
                GroupVH groupVH = (GroupVH) holder;
                groupVH.text1.setText(g.title);
                break;

            case TYPE_CHILD:
                Child c = (Child) item;
                ChildVH childVH = (ChildVH) holder;
                childVH.text1.setText(c.groupName);
                childVH.text2.setText(c.position + "");
                break;
        }
    }

    private void updateCheckStatus(final Message bean) {
        bean.setChecked(!bean.isChecked());
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
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
            info = itemView.findViewById(R.id.groupTitle);
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

    public class GroupVH extends ItemVH {
        public TextView text1;

        public GroupVH(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text1.setBackgroundColor(Color.RED);
        }

        @Override
        public int getType() {
            return TYPE_GROUP;
        }
    }

    public class ChildVH extends ItemVH {
        public TextView text1;
        public TextView text2;

        public ChildVH(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
            text1.setTextColor(Color.LTGRAY);
            text2.setTextColor(Color.BLUE);
        }

        @Override
        public int getType() {
            return TYPE_CHILD;
        }
    }

    public abstract class ItemVH extends RecyclerView.ViewHolder {
        public ItemVH(View itemView) {
            super(itemView);
        }

        public abstract int getType();
    }
}