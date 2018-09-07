package com.hades.android.example.android_about_demos.resource.xml;

import android.app.Fragment;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class ParseXmlFragment extends Fragment {

    private TextView show;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_xml, container, false);
        view.findViewById(R.id.parseXml).setOnClickListener(v -> parseXml());
        show = view.findViewById(R.id.tableContentList);
        return view;
    }

    private void parseXml() {
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


            show.setText(sb.toString());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

