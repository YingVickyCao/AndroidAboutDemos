package com.hades.android.example.android_about_demos.other_ui.dialog;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;

public class AlertDialogFragment extends Fragment {
    public static AlertDialogFragment newInstance() {
        Bundle args = new Bundle();
        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    TextView mShow;
    String[] items = new String[]{"A", "B", "C", "D"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_ui_alertdialog, container, false);

        mShow = view.findViewById(R.id.show);

        view.findViewById(R.id.simple).setOnClickListener(this::simple);
        view.findViewById(R.id.simpleList).setOnClickListener(this::simpleList);
        view.findViewById(R.id.singleChoice).setOnClickListener(this::singleChoice);
        view.findViewById(R.id.multiChoice).setOnClickListener(this::multiChoice);
        view.findViewById(R.id.customList).setOnClickListener(this::customList);
        view.findViewById(R.id.customView).setOnClickListener(this::customView);
        return view;
    }

    public void simple(View source) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getUsedContext())
                .setTitle("简单对话框")
//                .setCustomTitle()
                .setIcon(R.drawable.tools)
                .setMessage("对话框的测试内容\n第二行内容");
        setPositiveButton(builder);
        setNegativeButton(builder)
                .create()
                .show();
    }

    public void simpleList(View source) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getUsedContext())
                .setTitle("简单列表对话框")
                .setIcon(R.drawable.tools)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mShow.setText("Clicked " + items[which]);
                    }
                });

        setPositiveButton(builder);
        setNegativeButton(builder)
                .create()
                .show();
    }

    public void singleChoice(View source) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getUsedContext())
                .setTitle("单选列表项对话框")
                .setIcon(R.drawable.tools)
                // 设置单选列表项，默认选中第二项（索引为1）
                .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mShow.setText("Clicked " + items[which]);
                    }
                });
        setPositiveButton(builder);
        setNegativeButton(builder)
                .create()
                .show();
    }

    public void multiChoice(View source) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getUsedContext())
                .setTitle("多选列表项对话框")
                .setIcon(R.drawable.tools)
                // 设置多选列表项，设置勾选第2项、第4项
                .setMultiChoiceItems(items, new boolean[]{false, true, false, true}, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            mShow.setText("Checked " + items[which]);
                        } else {
                            mShow.setText("Unchecked " + items[which]);
                        }
                    }
                });
        setPositiveButton(builder);
        setNegativeButton(builder)
                .create()
                .show();
    }

    public void customList(View source) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getUsedContext())
                .setTitle("自定义列表项对话框")
                .setIcon(R.drawable.tools)
                .setAdapter(new ArrayAdapter<>(getUsedContext(), R.layout.other_ui_alertdialog_customList_itemview, items), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mShow.setText("Clicked " + items[which]);
                    }
                });
        setPositiveButton(builder);
        setNegativeButton(builder)
                .create()
                .show();
    }

    public void customView(View source) {
        TableLayout customView = (TableLayout) LayoutInflater.from(getUsedContext()).inflate(R.layout.other_ui_alertdialog_custom_view_login, null);
        new AlertDialog.Builder(getUsedContext())
                .setIcon(R.drawable.tools)
                .setTitle("自定义View对话框")
                // 设置对话框显示的View对象
                .setView(customView)
                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 此处可执行登录处理
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 取消登录，不做任何事情
                    }
                })
                // 创建并显示对话框
                .create()
                .show();
    }

    private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder) {
        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mShow.setText("单击了【确定】按钮！");
            }
        });
    }

    private AlertDialog.Builder setNegativeButton(
            AlertDialog.Builder builder) {
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mShow.setText("单击了【取消】按钮！");
            }
        });
    }

    private Context getUsedContext() {
        return getActivity();
    }

}