package com.example.hades.tdd.mvp.model;

/**
 * MVP - M,Model层的接口定义及实现
 * Model层是数据层，用来存储数据并且提供数据。
 */
public interface IWeatherModel {
    //提供数据
    String getInfo();

    //存储数据
    void setInfo(String info);
}
