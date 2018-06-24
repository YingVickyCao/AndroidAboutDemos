package com.hades.android.example.android_about_demos.other_ui.dialog;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.android.example.android_about_demos.R;

public class ProgressDialogFragment extends Fragment {

    final static int MAX_PROGRESS = 100;
    final static int UPDATE_PROGRESS = 1;

    // 该程序模拟填充长度为100的数组
    private int[] data = new int[50];
    // 记录进度对话框的完成百分比
    int progressStatus = 0;
    int hasData = 0;

    private ProgressDialog mPD;

    // 定义一个负责更新的进度的Handler
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE_PROGRESS) {
                // 更新进度条的进度
                // 第一进度 = 第二进度
                mPD.setProgress(progressStatus);

                // 第一进度 != 第二进度
                // 第一进度
                mPD.incrementProgressBy(1);
                // 第二进度
                mPD.incrementSecondaryProgressBy(progressStatus - 3);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_ui_progressdialog, container, false);
        view.findViewById(R.id.showCircleProgressDialog).setOnClickListener(this::showCircleProgressDialog);
        view.findViewById(R.id.showIndeterminateBarProgressDialog).setOnClickListener(this::showIndeterminateBarProgressDialog);
        view.findViewById(R.id.showBarProgressDialog).setOnClickListener(this::showBarProgressDialog);

        view.findViewById(R.id.showCircleProgressDialogWithBtns).setOnClickListener(this::showCircleProgressDialogWithBtns);
        view.findViewById(R.id.showIndeterminateBarProgressDialogWithBtns).setOnClickListener(this::showIndeterminateBarProgressDialogWithBtns);
        view.findViewById(R.id.showBarProgressDialogWithBtns).setOnClickListener(this::showBarProgressDialogWithBtns);
        return view;
    }

    private Context getUsedContext() {
        return getActivity();
    }

    public void showCircleProgressDialog(View source) {
        ProgressDialog.show(getUsedContext(), "任务执行中", "任务执行中，请等待", false, true); // ①
    }

    private void showCircleProgressDialogWithBtns(View source) {
        ProgressDialog pd = new ProgressDialog(getUsedContext());
        pd.setIcon(R.drawable.tools);
        pd.setTitle("任务正在执行中");
        pd.setMessage("任务正在执行中，敬请等待...");
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // 是否模糊显示进度
        pd.setIndeterminate(false);
        pd.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        pd.dismiss();
                    }
                });
        pd.setButton(DialogInterface.BUTTON_NEUTRAL, "中立",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
        pd.show();
    }

    public void showIndeterminateBarProgressDialog(View source) {
        ProgressDialog pd = new ProgressDialog(getUsedContext());
        pd.setIcon(R.drawable.tools);
        pd.setTitle("任务正在执行中");
        pd.setMessage("任务正在执行中，敬请等待...");
        pd.setCancelable(true);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 是否模糊显示进度
        pd.setIndeterminate(true);
        pd.show();
    }

    public void showIndeterminateBarProgressDialogWithBtns(View source) {
        ProgressDialog pd = new ProgressDialog(getUsedContext());
        pd.setIcon(R.drawable.tools);
        pd.setTitle("任务正在执行中");
        pd.setMessage("任务正在执行中，敬请等待...");
        pd.setCancelable(true);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 是否模糊显示进度
        pd.setIndeterminate(true);
        pd.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        pd.dismiss();
                    }
                });
        pd.setButton(DialogInterface.BUTTON_NEUTRAL, "中立",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
        pd.show();
    }

    public void showBarProgressDialog(View source) {
        progressStatus = 0;
        hasData = 0;
        mPD = new ProgressDialog(getUsedContext());
        mPD.setMax(MAX_PROGRESS);
        mPD.setTitle("任务完成百分比");
        mPD.setMessage("耗时任务的完成百分比");
        mPD.setCancelable(false);
        mPD.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 是否模糊显示进度
        mPD.setIndeterminate(false);
        mPD.show();

        new Thread() {
            public void run() {
                while (progressStatus < MAX_PROGRESS) {
                    // 获取耗时操作的完成百分比
                    progressStatus = MAX_PROGRESS * mockHeavyWork() / data.length;
                    // 发送空消息到Handler
                    handler.sendEmptyMessage(UPDATE_PROGRESS);
                }

                // 如果任务已经完成
                if (progressStatus >= MAX_PROGRESS) {
                    /**
                     * 关闭对话框
                     */
                    mPD.dismiss();
                }
            }
        }.start();
    }

    public void showBarProgressDialogWithBtns(View source) {
        progressStatus = 0;
        hasData = 0;
        mPD = new ProgressDialog(getUsedContext());
        mPD.setMax(MAX_PROGRESS);
        mPD.setTitle("任务完成百分比");
        mPD.setMessage("耗时任务的完成百分比");
        mPD.setCancelable(false);
        mPD.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 是否模糊显示进度
        mPD.setIndeterminate(false);

        mPD.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
        mPD.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        mPD.dismiss();
                    }
                });
        mPD.setButton(DialogInterface.BUTTON_NEUTRAL, "中立",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
        mPD.show();

        new Thread() {
            public void run() {
                while (progressStatus < MAX_PROGRESS) {
                    // 获取耗时操作的完成百分比
                    progressStatus = MAX_PROGRESS * mockHeavyWork() / data.length;
                    // 发送空消息到Handler
                    handler.sendEmptyMessage(UPDATE_PROGRESS);
                }

                // 如果任务已经完成
                if (progressStatus >= MAX_PROGRESS) {
                    /**
                     * 关闭对话框
                     */
                    mPD.dismiss();
                }
            }
        }.start();
    }

    public int mockHeavyWork() {
        data[hasData++] = (int) (Math.random() * 100);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }
}
