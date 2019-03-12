package com.hades.example.android.android_about_demos.resource.drawable.bitmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hades.example.android.android_about_demos.R;

import java.util.List;

public class ImageDisplayAdapter extends BaseAdapter {
    private List<ImageDisplayItem> mList;
    private Context mContext;

    public ImageDisplayAdapter(List<ImageDisplayItem> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return null == mList ? 0 : mList.size();
    }

    @Override
    public ImageDisplayItem getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        // 如果 convertView 为 null，就填充视图
        if (convertView == null) {
            // 创建视图
            convertView = LayoutInflater.from(mContext).inflate(R.layout.res_memory_cache_bitmap_item_view, null);

            viewHolder = new ViewHolder();
            // 获取视图中各个控件的引用，并存入viewHolder中
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            viewHolder.number = convertView.findViewById(R.id.number);

            // 把ViewHolder存入标记中
            // PO: [ListView][Adapter] convertView.setTag(viewHolder)
            convertView.setTag(viewHolder);

        } else {
            // 如果convertView 非null， 则回收利用它。
            // convertView的标记中获取viewHolder
            // PO: [ListView][Adapter] viewHolder = (ViewHolder) convertView.getTag()
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 根据视图的索引位置，获取 model 对象
        ImageDisplayItem model = getItem(position);

        // 填充视图
        // 使用 model 中的数据填充该视图
        viewHolder.imageView.setImageResource(model.getImageResId());
        viewHolder.number.setText(model.getNum());
        return convertView;
    }

    // ViewHolder 类
    // ViewHolder中变量存储所有UI控件的引用
    private static class ViewHolder {
        TextView number;
        ImageView imageView;
    }
}
