package com.example.hades.tdd.mvp.model;

/**
 * MVP - M, Model
 */
public class WeatherModelImpl implements IWeatherModel {
    private String mWeatherInfo = "windy";

    @Override
    public String getInfo() {
        return mWeatherInfo;
    }

    @Override
    public void setInfo(String info) {
        mWeatherInfo = info;
    }
}