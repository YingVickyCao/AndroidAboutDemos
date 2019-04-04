package com.example.hades.tdd.mvp;

import com.example.hades.tdd.mvp.model.IWeatherModel;
import com.example.hades.tdd.mvp.presenter.IWeatherPresenter;
import com.example.hades.tdd.mvp.presenter.WeatherPresenter;
import com.example.hades.tdd.mvp.view.IWeatherView;

import net.jodah.concurrentunit.Waiter;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherTest {

    @Before
    public void init() {
        System.out.println("init");
        mWeatherModel = mock(IWeatherModel.class);
        mWeatherView = mock(IWeatherView.class);

        mWeatherPresenter = new WeatherPresenter();
        mWeatherPresenter.setView(mWeatherView);
        mWeatherPresenter.setWeatherModel(mWeatherModel);
    }

    @Test
    public void testRequestWeather() throws Exception {
        when(mWeatherModel.getInfo()).thenReturn("Cloudy");
        mWeatherPresenter.resetWeatherInfo();
        mWeatherPresenter.requestWeatherInfo();
        System.out.println(mWeatherModel.getInfo());
    }

    @Test
    public void testRequestWeather2() throws Exception {
        when(mWeatherModel.getInfo()).thenReturn("Cloudy");
        final Waiter waiter = new Waiter();
        mWeatherPresenter.resetWeatherInfo();
        mWeatherPresenter.requestWeatherInfo();
        waiter.await();
    }


    private IWeatherModel mWeatherModel;
    private IWeatherView mWeatherView;
    private IWeatherPresenter mWeatherPresenter;
}
