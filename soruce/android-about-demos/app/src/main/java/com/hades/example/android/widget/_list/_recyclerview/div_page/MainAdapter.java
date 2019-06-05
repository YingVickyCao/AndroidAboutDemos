package com.hades.example.android.widget._list._recyclerview.div_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hades.example.android.R;
import com.hades.example.android.lib.mock.DummyItem;
import com.hades.example.android.widget._list._recyclerview.dummy.DummyRecyclerViewAdapter;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter {
    Context context;
    LoadMoreListener listener;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_BOTTOM = 2;

    public int loadState;
    public static final int STATE_LOADING = 1;
    public static final int STATE_LASTED = 2;
    public static final int STATE_ERROR = 3;

    public boolean hasMore = true;
    public boolean isLoading = false;

    private int NUM_IN_ONE_PAGE = 5;//每一页和后台说定的条数
    public List<DummyItem> list;


    public MainAdapter(Context context, List<DummyItem> list, LoadMoreListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    protected RecyclerView.ViewHolder setItemViewHolder(ViewGroup parent, int viewType) {
        return new DummyRecyclerViewAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.widget_recyclerview_dummy_item, parent, false));
    }

    protected void LoadingMore() {
        if (listener == null) {
            return;
        }
        listener.loadMoreData();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BOTTOM) {
            return new BottomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_recyclerview_scroll_to_bottom_view, parent, false));
        } else {
            return setItemViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (TYPE_BOTTOM == getItemViewType(position)) {
            final ProgressBar progressBar = ((BottomViewHolder) holder).progressBar;
            final TextView bottomTextView = ((BottomViewHolder) holder).bottomTextView;
            final ImageView bottomIcon = ((BottomViewHolder) holder).bottomIcon;

            switch (loadState) {
                case STATE_LOADING:
                    progressBar.setVisibility(View.VISIBLE);
                    bottomIcon.setVisibility(View.GONE);
                    bottomTextView.setText("加载中");
                    holder.itemView.setOnClickListener(null);
                    hasMore = true;
                    break;
                case STATE_LASTED:
                    progressBar.setVisibility(View.GONE);
                    bottomIcon.setVisibility(View.VISIBLE);
                    bottomIcon.setImageResource(R.drawable.info_icon);
                    bottomTextView.setText("没有更多了");
                    holder.itemView.setOnClickListener(null);
                    hasMore = false;
                    break;
                case STATE_ERROR:
                    progressBar.setVisibility(View.GONE);
                    bottomIcon.setVisibility(View.VISIBLE);
                    bottomIcon.setImageResource(R.drawable.error_icon);
                    bottomTextView.setText("加载失败请点击重试");
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            bottomIcon.setVisibility(View.GONE);
                            bottomTextView.setText("加载中");
                            LoadingMore();
                        }
                    });
                    hasMore = true;
                    break;
            }
        } else {
            onBindItemViewHolder(holder, position);
        }
    }

    private void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DummyRecyclerViewAdapter.ViewHolder) holder).id.setText(String.valueOf(list.get(position).getId()));
        ((DummyRecyclerViewAdapter.ViewHolder) holder).title.setText(list.get(position).getColo2());
    }

    @Override
    public int getItemViewType(int position) {
        if (list.size() < NUM_IN_ONE_PAGE) {
            return TYPE_ITEM;
        } else {
            if (position == list.size()) {
                return TYPE_BOTTOM;
            } else {
                return TYPE_ITEM;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() < NUM_IN_ONE_PAGE) {
            hasMore = false;
            return list.size();
        } else {
            return list.size() + 1;
        }
    }

    public void setErrorStatus() {
        loadState = STATE_ERROR;
        notifyItemChanged(list.size());
        setLoading(false);
    }

    public void setLastedStatus() {
        loadState = STATE_LASTED;
        notifyItemChanged(list.size());
    }


    public void addList(List addList) {
        int count = this.list.size();
        this.list.addAll(addList);
        notifyItemRangeChanged(count, addList.size());
        setLoading(false);
    }

    public int getNUM_IN_ONE_PAGE() {
        return NUM_IN_ONE_PAGE;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public interface LoadMoreListener {
        void loadMoreData();
    }

    public class BottomViewHolder extends RecyclerView.ViewHolder {

        TextView bottomTextView;
        ImageView bottomIcon;
        ProgressBar progressBar;

        public BottomViewHolder(View itemView) {
            super(itemView);
            bottomTextView = (TextView) itemView.findViewById(R.id.bottom_title);
            bottomIcon = (ImageView) itemView.findViewById(R.id.bottom_icon);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
        }
    }
}