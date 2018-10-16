package com.hades.android.example.android_about_demos.resource.material;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseFragment;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class MaterialFragment extends BaseFragment {
    MediaPlayer mediaPlayer1 = null;
    MediaPlayer mediaPlayer2 = null;
    private ImageView mImageView;
    private TextView mXML;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_material, container, false);

        mImageView = view.findViewById(R.id.imageView);
        mXML = view.findViewById(R.id.tableContentList);

        view.findViewById(R.id.parseXML).setOnClickListener(v -> parseXML());
        view.findViewById(R.id.playRaw).setOnClickListener(v -> playRaw());
        view.findViewById(R.id.playAsserts).setOnClickListener(arg0 -> playAsserts());
        view.findViewById(R.id.loadAssertsImg).setOnClickListener(arg0 -> loadAssertsImg());

        mediaPlayer1 = MediaPlayer.create(getActivity(), R.raw.msg);

        try {
            AssetManager am = getActivity().getAssets();
            // 获取指定文件对应的AssetFileDescriptor
            AssetFileDescriptor afd = am.openFd("shot.mp3");
            mediaPlayer2 = new MediaPlayer();
            // 使用MediaPlayer加载指定的声音文件
            mediaPlayer2.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getStartOffset());
            mediaPlayer2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void parseXML() {
        // 根据XML资源的ID获取解析该资源的解析器
        // XmlResourceParser是XmlPullParser的子类
        XmlResourceParser xrp = getResources().getXml(R.xml.books);
        try {
            StringBuilder sb = new StringBuilder("");
            // 还没有到XML文档的结尾处
            while (xrp.getEventType()
                    != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    // 获取该标签的标签名
                    String tagName = xrp.getName();
                    // 如果遇到book标签
                    if (tagName.equals("book")) {
                        // 根据属性名来获取属性值
                        String bookName = xrp.getAttributeValue(null, "price");
                        sb.append("价格：");
                        sb.append(bookName);
                        // 根据属性索引来获取属性值
                        String bookPrice = xrp.getAttributeValue(1);
                        sb.append(" 出版日期：");
                        sb.append(bookPrice);
                        sb.append(" 书名：");
                        // 获取文本节点的值
                        sb.append(xrp.nextText());
                    }
                    sb.append("\n");
                }
                // 获取解析器的下一个事件
                xrp.next(); // ①
            }

            mXML.setText(sb.toString());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playRaw() {
        mediaPlayer1.start();
    }

    private void playAsserts() {
        // TODO: 03/07/2018 没有声音
        mediaPlayer2.start();
    }

    private void loadAssertsImg() {
        InputStream is = null;
        try {
            is = getActivity().getAssets().open("play_fire.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        mImageView.setImageBitmap(bitmap);

    }
}