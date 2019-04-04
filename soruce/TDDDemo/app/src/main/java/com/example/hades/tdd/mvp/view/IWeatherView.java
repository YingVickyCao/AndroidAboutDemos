/*******************************************************************************
 * Copyright (c) 2012 Manning
 * See the file license.txt for copying permission.
 ******************************************************************************/
package com.example.hades.tdd.mvp.view;

/**
 * MVP - V,View层的接口定义及实现
 * Activity/Fragment用来专注视图的表现。
 */
public interface IWeatherView {
    // 显示天气信息
    void showWeatherInfo(String info);

    void removeWeatherInfo();

    // 显示获取信息等待对话框
    void showWaitingDialog();

    // 取消显示对话框
    void dismissWaitingDialog();

}
