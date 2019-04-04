package com.example.hades.tdd.mvp.presenter;

import com.example.hades.tdd.mvp.model.IWeatherModel;
import com.example.hades.tdd.mvp.view.IWeatherView;

/**
 * MVP - P,Presenter层
 * 同时对View和Model对接，所以内部必须持有它们的接口引用。
 */
public interface IWeatherPresenter {
    void setView(IWeatherView view);

    void setWeatherModel(IWeatherModel mWeatherModel);

    void resetWeatherInfo();

    void requestWeatherInfo();
}
