package com.hades.example.android._process_and_thread._asynctask;

public interface ISum {
    void onPreExecute(String msg);

    void setProgress(int progress);

    void setResult(long result);

}
