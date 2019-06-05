package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v2;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hades.example.android.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.ItemViewHolder> implements IItemTouchHelperAdapter {
    private static final String TAG = ItemTouchHelperAdapter.class.getSimpleName();

    List<Message> mList;
    private IDragView mDragView;
    private int mGroupResId;
    private Activity mContext;
    private List<Integer> mExpandPositionList = new ArrayList<>();
    private boolean isOnDrag = false;

    private String type;

    private List<VHPositionHashCodeBean> mViewHolderPositionHashCodeList = new ArrayList<>();
    private VHPositionHashCodeBean mCheckContain = new VHPositionHashCodeBean();

    public ItemTouchHelperAdapter(List<Message> list, Activity context) {
        mList = list;
        mContext = context;
    }

    public void setDragView(IDragView dragView) {
        mDragView = dragView;
    }

    public void setGroupResId(int resId) {
        mGroupResId = resId;
    }

    public void resetIsOnDragTag() {
        isOnDrag = false;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_recyclerview_4_drag_reorder_item_view_v2_1, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(mGroupResId, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        mViewHolderPositionHashCodeList.add(new VHPositionHashCodeBean(0, itemViewHolder.hashCode()));
        Log.d(TAG, "onCreateViewHolder: @ItemViewHolder=" + itemViewHolder.hashCode() + ",vh list=" + mViewHolderPositionHashCodeList.size());
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        mCheckContain.hashCode = holder.hashCode();
        if (mViewHolderPositionHashCodeList.contains(mCheckContain)) {
            int index = mViewHolderPositionHashCodeList.indexOf(mCheckContain);
            if (-1 != index) {
                VHPositionHashCodeBean bean = mViewHolderPositionHashCodeList.get(index);
                bean.position = position;
            }
        }
        else{
            mViewHolderPositionHashCodeList.add(new VHPositionHashCodeBean(position, holder.hashCode()));
        }

        Log.d(TAG, "onBindViewHolder: position=" + position + ",@ItemViewHolder=" + holder.hashCode() + ",vh list=" + mViewHolderPositionHashCodeList.size());

//        Log.d(TAG, "onBindViewHolder: VH:\n" + mViewHolderPositionHashCodeList.toString());

        Message bean = mList.get(position);
        holder.groupTitle.setText(bean.getTitle());
        holder.drag.setOnLongClickListener(v -> {
            if (isOnDrag) {
                return true;
            }
            if (null != mDragView) {
                mDragView.showLoading();
            }
            new Thread(() -> {
                if (isHasExpand()) {
                    collapse();
                } else {
                    isOnDrag = true;
                    mExpandPositionList.clear();
                    if (null != mDragView) {
                        mDragView.startDrag(holder);
                    }
                }
            }).start();
            return true;
        });

        List<Child> children = bean.getChildren();
//        int preChildCount = holder.childContainer.getChildCount();
        if (children == null || children.isEmpty() || children.size() != holder.childContainer.getChildCount()) {
            holder.childContainer.removeAllViews();
        }

        if (null != children && !children.isEmpty() && children.size() != holder.childContainer.getChildCount()) {
            for (int i = 0; i < children.size(); i++) {
                TextView textView = (TextView) LayoutInflater.from(holder.drag.getContext()).inflate(android.R.layout.simple_list_item_activated_1, null);
                textView.setBackgroundColor(Color.WHITE);
                String childText = String.valueOf(children.get(i).childText);
                textView.setText(childText);
                textView.setOnClickListener(v -> openPage(bean.getTitle(), childText, bean, false));
                holder.childContainer.addView(textView);
            }
        }

        if (holder.childContainer.getChildCount() > 0) {
            holder.groupContainer.setOnClickListener(v -> toggleExpand(holder.childContainer, bean, position));
        } else {
            holder.groupContainer.setOnClickListener(v -> openPage(bean.getTitle(), null, bean, true));
        }

//        Log.d(TAG, "onBindViewHolder: position=" + position + ",isExpand=" + bean.isExpand() + ",@ItemViewHolder=" + holder.hashCode() + ",ChildCount=" + preChildCount + "->" + holder.childContainer.getChildCount());

        boolean isExpand = holder.childContainer.getChildCount() > 0 && bean.isExpand();
        holder.childContainer.setVisibility(isExpand ? View.VISIBLE : View.GONE);
    }

    @Override
    public long getItemId(int position) {
        return (mList.get(position).getId()) - 1;
    }

    private void openPage(String title, String childTitle, Message bean, boolean isGroup) {
        if (null != mDragView) {
            mDragView.openPage(bean, isGroup, title, childTitle);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
//        Log.d(TAG, "onItemMove: " + "thread name=" + Thread.currentThread().getName() + ",thread id=" + Thread.currentThread().getId()); // onItemMove: thread name=main,thread id=2
        Collections.swap(mList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        Log.d(TAG, "onItemDismiss: " + "thread name=" + Thread.currentThread().getName() + ",thread id=" + Thread.currentThread().getId());
        mList.remove(position);
        notifyItemRemoved(position);
    }

    // PO: 2019-06-03
    private void toggleExpand(View view, final Message bean, int position) {
        if (isOnDrag) {
            return;
        }
        Log.d(TAG, "toggleExpand:start" + mExpandPositionList.toString());
        if (null != mDragView) {
            mDragView.showLoading();
        }

        new Thread(() -> {
            bean.setExpand(!bean.isExpand());
//        notifyDataSetChanged();
            if (bean.isExpand()) {
                mExpandPositionList.add(position);
            } else {
//                int index = mExpandPositionList.indexOf(position);
//                if (-1 != index && index <= (mExpandPositionList.size() - 1)) {
//                    mExpandPositionList.remove(index);
//                }
            }
            Log.d(TAG, "toggleExpand:end" + mExpandPositionList.toString());

            mContext.runOnUiThread(() -> {
                view.setVisibility(bean.isExpand() ? View.VISIBLE : View.GONE);
            });
            if (null != mDragView) {
                mDragView.hideLoading();
            }

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

        mContext.runOnUiThread(this::notifyDataSetChanged);

        if (null != mDragView) {
            mDragView.hideLoading();
        }
        Log.d(TAG, "collapse:end" + mExpandPositionList.toString());
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder implements IItemViewHolder {
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