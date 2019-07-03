package com.hades.example.android.other_ui._dialog.depressed;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestAlertDialogFragment extends BaseFragment {

    TextView mShow;
    String[] items = new String[]{"A", "B", "C", "D"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_ui_alertdialog, container, false);

        mShow = view.findViewById(R.id.tableContentList);

        view.findViewById(R.id.simple).setOnClickListener(this::simple);
        view.findViewById(R.id.simple_with_style).setOnClickListener(v -> simple_with_style());
        view.findViewById(R.id.simpleList).setOnClickListener(source -> simpleList());
        view.findViewById(R.id.singleChoice).setOnClickListener(this::singleChoice);
        view.findViewById(R.id.multiChoice).setOnClickListener(this::multiChoice);
        view.findViewById(R.id.customList).setOnClickListener(this::customList);
        view.findViewById(R.id.customView).setOnClickListener(this::customView);
        view.findViewById(R.id.customView_style).setOnClickListener(v -> customView_style());
        return view;
    }

    public void simple(View source) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getUsedContext(), R.style.AlertDialogStyle)
                .setTitle("简单对话框")
//                .setCustomTitle()
                .setIcon(R.drawable.tools)
                .setNeutralButton("Neutral", null)
                .setPositiveButton("Confirm", (dialog, which) -> mShow.setText("Clicked Confirm btn"))
                .setMessage("对话框的测试内容\n第二行内容");
        setPositiveButton(builder);
        setNegativeButton(builder)
                .create()
                .show();
    }

    private void simple_with_style() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getUsedContext())
                .setTitle("简单对话框")
//                .setCustomTitle()
                .setIcon(R.drawable.tools)
                .setNeutralButton("Neutral", null)
                .setPositiveButton("Confirm", (dialog, which) -> mShow.setText("Clicked Confirm btn"))
                .setMessage("对话框的测试内容\n第二行内容");
        setPositiveButton(builder);
        setNegativeButton(builder)
                .create()
                .show();
    }

    public void simpleList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getUsedContext())
                .setTitle("A traditional single-choice list")
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
                .setTitle("A persistent single-choice list (radio buttons)")
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
                .setTitle("A persistent multiple-choice list (checkboxes)")
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
                .setAdapter(new ArrayAdapter<>(getUsedContext(), R.layout.other_ui_alertdialog_customlist_itemview, items), new DialogInterface.OnClickListener() {
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
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Pass null as the parent view because its going in the dialog layout
        TableLayout customView = (TableLayout) inflater.inflate(R.layout.other_ui_alertdialog_custom_view_login, null);
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

    private void customView_style() {
        View customView = LayoutInflater.from(getUsedContext()).inflate(R.layout.other_ui_alertdialog_4_shadow_color, null);

//        new AlertDialog.Builder(getUsedContext(), R.style.CustomAlertDialogStyle2)
        Dialog dialog =
                new AlertDialog.Builder(getUsedContext(), R.style.AlertDialogStyle)
                        .setIcon(R.drawable.tools)
                        .setTitle("自定义View对话框")
                        // 设置对话框显示的View对象
                        .setView(customView)
//                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // 此处可执行登录处理
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // 取消登录，不做任何事情
//                    }
//                })
                        // 创建并显示对话框
                        .create();
        dialog.show();
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