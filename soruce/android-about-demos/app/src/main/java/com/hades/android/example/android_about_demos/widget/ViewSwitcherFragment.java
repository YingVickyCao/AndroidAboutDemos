package com.hades.android.example.android_about_demos.widget;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.hades.android.example.android_about_demos.R;

import java.util.ArrayList;

public class ViewSwitcherFragment extends Activity {
    //定义一个常量，用于显示每屏显示的运用程序数
    public static final int NUMBER_PER_SCREEN = 8;

    //代表运用程序的内部类
    public static class DataItem {
        //运用程序名称
        public String dataName;
        //运用程序图标
        public Drawable drawable;
    }

    //保存系统所有应用程序的LIST集合
    private ArrayList<DataItem> items = new ArrayList<DataItem>();
    //记录当前正在显示第几屏的程序
    private int screenNo = -1;
    private int screenCount;
    ViewSwitcher switcher;
    //创建LayoutInflater对象
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        inflater = LayoutInflater.from(ViewSwitcherFragment.this);
        //创建一个包含40个元素的list集合，用于模拟包含40个应用程序
        for (int i = 0; i < 40; i++) {
            String label = "" + i;
            Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);

            DataItem item = new DataItem();
            item.dataName = label;
            item.drawable = drawable;
            items.add(item);

        }
        //计算应用程序所展的总屏数
        screenCount = items.size() % NUMBER_PER_SCREEN == 0 ?
                items.size() / NUMBER_PER_SCREEN : items.size() / NUMBER_PER_SCREEN + 1;
        switcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                //加载R.layout.slidelistview 组件，实际上就是一个GridView组件
                return inflater.inflate(R.layout.slidelistview, null);
            }

        });
        //页面加载时先显示第一屏
        next(null);
    }

    public void next(View v) {
        if (screenNo < screenCount - 1) {
            screenNo++;
            //为viewSwitcher的组件显示过程动画
            switcher.setInAnimation(this, R.anim.slide_in_right);
            //为viewSwitcher的组件隐藏过程动画
            switcher.setOutAnimation(this, R.anim.slide_out_left);
            //控制下一屏将要显示的GridView对应的Adapter
            ((GridView) switcher.getNextView()).setAdapter(adapter);
            //单击右边按钮，显示下一屏
            //学习手势检测后，也可通过手势检测实现显示下一屏
            switcher.showNext();
        }

    }

    public void prev(View v) {
        if (screenNo > 0) {
            screenNo--;
            switcher.setInAnimation(this, R.anim.slide_in_right);
            switcher.setOutAnimation(this, R.anim.slide_out_left);

            ((GridView) switcher.getNextView()).setAdapter(adapter);
            switcher.showPrevious();
        }
    }

    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            //如果已经到了最后一屏，且应用程序的数量不能整除NUMBER_PER_SCREEN
            if (screenNo == screenCount - 1 && items.size() % NUMBER_PER_SCREEN != 0) {
                //最后一屏且应用程序的数量对NUMBER_PER_SCREEN求余
                return items.size() % NUMBER_PER_SCREEN;
            }
            //否则每屏显示的程序数量为NUMBER_PER_SCREEN
            return NUMBER_PER_SCREEN;
        }

        @Override
        public DataItem getItem(int position) {
            //根据screenNo计算第posotion个列表的数据
            return items.get(screenNo * NUMBER_PER_SCREEN + position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView == null) {
                //加载R.layout.labelicon布局文件
                view = inflater.inflate(R.layout.lableicon, null);
            }
            //获取R.layout.labelicon布局文件中的ImageView组件，并为之设置图标
            ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
            imageView.setImageDrawable(getItem(position).drawable);
            TextView textView = (TextView) view.findViewById(R.id.textview);
            textView.setText(getItem(position).dataName);
            return view;
        }
    };
}
