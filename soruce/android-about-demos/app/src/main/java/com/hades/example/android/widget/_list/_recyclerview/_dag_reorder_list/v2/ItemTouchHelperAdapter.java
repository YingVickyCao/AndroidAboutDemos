package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v2;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hades.example.android.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.ItemViewHolder> implements IItemTouchHelperAdapter {
    private static final String TAG = ItemTouchHelperAdapter.class.getSimpleName();

    List<Group> mList;
    private IDragView mDragView;
    private int mGroupResId;
    private Activity mContext;
    private boolean isOnDrag = false;
    private List<VHPositionHashCodeBean> mViewHolderPositionHashCodeList = new ArrayList<>();
    private VHPositionHashCodeBean mCheckContain = new VHPositionHashCodeBean();
    private int minPos = 0;
    private int maxPos = 0;
    private OpenedPage mOpenedPage;
    private int type;
    private boolean isLimitMode;

    public ItemTouchHelperAdapter(List<Group> list, Activity context) {
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

    public void setOpenedPage(OpenedPage openedPage) {
        mOpenedPage = openedPage;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setLimitMode(boolean limitMode) {
        isLimitMode = limitMode;
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
        } else {
            mViewHolderPositionHashCodeList.add(new VHPositionHashCodeBean(position, holder.hashCode()));
        }

        Log.d(TAG, "onBindViewHolder: position=" + position + ",@ItemViewHolder=" + holder.hashCode() + ",vh list=" + mViewHolderPositionHashCodeList.size());

        Group bean = mList.get(position);
        boolean isSelected = null != mOpenedPage && null != mOpenedPage.getTitle() && mOpenedPage.getTitle().equals(bean.getTitle());
        holder.check.setSelected(isSelected);
        holder.check.setTag(isSelected);
        holder.groupTitle.setText(bean.getTitle());
        holder.drag.setOnLongClickListener(v -> longClickDrag(holder));
        holder.drag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                printMotionEvent(event);
                return false;
            }
        });
        onBindViewHolder4ChildContainer(holder, position, bean);
        if (holder.childContainer.getChildCount() > 0) {
            holder.groupContainer.setOnClickListener(v -> toggleExpand(holder.childContainer, bean, position));
        } else {
            holder.groupContainer.setOnClickListener(v -> openPage(bean.getTitle(), null, bean, true));
        }
        //        Log.d(TAG, "onBindViewHolder: position=" + position + ",isExpand=" + bean.isExpand() + ",@ItemViewHolder=" + holder.hashCode() + ",ChildCount=" + preChildCount + "->" + holder.childContainer.getChildCount());
    }

    private void printMotionEvent(MotionEvent event) {
        Log.d(TAG, "printMotionEvent: event action=" + event.getAction() + ",rawX=" + event.getRawX() + ",rawY=" + event.getRawY());
    }

    private void onBindViewHolder4ChildContainer(final ItemViewHolder holder, int position, Group bean) {
        List<Child> children = bean.getChildren();
        if (null == children || children.isEmpty()) {
            holder.childContainer.setVisibility(View.GONE);
            return;
        }

        holder.childContainer.setVisibility(View.VISIBLE);
        int dataChildCount = children.size();
        int viewChildCount = holder.childContainer.getChildCount();

        String compare = "";
        if (viewChildCount < dataChildCount) {
            compare = "<";
        } else if (viewChildCount == dataChildCount) {
            compare = "=";
        } else {
            compare = ">";
        }

        Log.d(TAG, "onBindViewHolder4ChildContainer: position=" + position + ",dataChildCount=" + dataChildCount + "  " + compare + "  " + "viewChildCount=" + viewChildCount);

        if (viewChildCount < dataChildCount) {
            for (int i = 0; i < dataChildCount - viewChildCount; i++) {
                Log.d(TAG, "onBindViewHolder4ChildContainer: <  " + " add child " + (i + 1));
                View child = LayoutInflater.from(holder.drag.getContext()).inflate(R.layout.simple_list_item_activated_1, null);
                holder.childContainer.addView(child);
            }

        } else if (viewChildCount == dataChildCount) {

        } else {
            try {
                holder.groupContainer.removeViews(dataChildCount, viewChildCount - dataChildCount - 1);
            }catch (IndexOutOfBoundsException ex){
                Log.d(TAG, "onBindViewHolder4ChildContainer: IndexOutOfBoundsException");
            }

        }

        Log.d(TAG, "onBindViewHolder4ChildContainer:dataChildCount=" + dataChildCount + ",viewChildCount=" + holder.childContainer.getChildCount());

        for (int i = 0; i < dataChildCount; i++) {
            View child = holder.childContainer.getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            TextView textView = child.findViewById(R.id.text1);
            String childText = String.valueOf(children.get(i).childText);
            textView.setText(childText);
            if (null != mOpenedPage && null != mOpenedPage.getChildTitle() && childText.equals(mOpenedPage.getChildTitle())) {
                textView.setTextColor(holder.drag.getContext().getResources().getColor(android.R.color.holo_blue_light));
            } else {
                textView.setTextColor(holder.drag.getContext().getResources().getColor(android.R.color.black));
            }
            Log.d(TAG, "onBindViewHolder4ChildContainer: childText=" + childText);
            textView.setOnClickListener(v -> openPage(bean.getTitle(), childText, bean, false));
        }
        holder.childContainer.applyStatus();
    }

    private void resetPositionRegion() {
        minPos = 0;
        maxPos = 0;
    }

    @Override
    public long getItemId(int position) {
        return (mList.get(position).getId()) - 1;
    }

    private void openPage(String title, String childTitle, Group bean, boolean isGroup) {
        if (null != mDragView) {
            mDragView.openPage(bean, isGroup, title, childTitle);
        }
    }

    @Override
    public int getItemCount() {
        int size = mList.size();

        if (DragAndReorderListFragment.TYPE_1 == type) {
            if (!isLimitMode) {
                return size;
            } else {
                if (size >= 3) {
                    return 3;
                } else {
                    return size;
                }
            }

        } else {
            return size;
        }
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

    private void toggleExpand(ToggleLinearLayout view, final Group bean, int position) {
        if (isOnDrag) {
            return;
        }
        if (null != mDragView) {
            mDragView.showLoading();
        }
        view.toggleExpandStatus();
        new Thread(() -> {
            if (view.getExpandStatus()) {
                if (position > maxPos) {
                    maxPos = position;
                } else if (position < minPos) {
                    minPos = position;
                }
            }
            if (null != mDragView) {
                mDragView.hideLoading();
            }
        }).start();
    }

    private boolean longClickDrag(ItemViewHolder holder) {
        if (isOnDrag) {
            return true;
        }
        if (null != mDragView) {
            mDragView.showLoading();
        }
        new Thread(() -> {
//            if (isHasExpand()) {
//                collapse();
//            } else
            {
                isOnDrag = true;
                resetPositionRegion();
                if (null != mDragView) {
                    mDragView.startDrag(holder);
                }
            }
        }).start();
        return true;
    }

    private boolean isHasExpand() {
        return minPos != 0 || maxPos != 0;
    }

    private void collapse() {
//        mContext.runOnUiThread(() -> notifyItemRangeChanged(minPos, maxPos - minPos + 1));(() -> notifyItemRangeChanged(minPos, maxPos - minPos + 1));
        mContext.runOnUiThread(this::notifyDataSetChanged);
        if (null != mDragView) {
            mDragView.hideLoading();
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder implements IItemViewHolder {
        private View root;
        private TextView groupTitle;
        private Button drag;
        private ImageView check;
        private ToggleLinearLayout childContainer;
        private ViewGroup groupContainer;

        ItemViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            groupTitle = itemView.findViewById(R.id.groupTitle);
            drag = itemView.findViewById(R.id.drag);
            check = itemView.findViewById(R.id.check);
            childContainer = itemView.findViewById(R.id.childContainer);
            groupContainer = itemView.findViewById(R.id.groupContainer);
        }

        // QA: onItemSelected - show item view bg when drag dragBtn
        @Override
        public void onItemSelected() {
            root.setSelected(true);
            updateCheck();
        }

        @Override
        public void onItemClear() {
            root.setSelected(false);
            updateCheck();
        }

        private void updateCheck() {
            if (null != check.getTag() && check.getTag() instanceof Boolean) {
                boolean selected = (boolean) check.getTag();
                check.setSelected(selected);
            }
        }
    }
}