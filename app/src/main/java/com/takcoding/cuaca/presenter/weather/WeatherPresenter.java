package com.takcoding.cuaca.presenter.weather;

import com.takcoding.cuaca.model.WeatherResponse;
import com.takcoding.cuaca.util.GenericErrorResponseBean;

public class WeatherPresenter implements WeatherContract.Presenter, WeatherContract.Model.OnFinishedListener {

    private WeatherContract.View view;
    private WeatherContract.Model model;

    public WeatherPresenter(WeatherContract.View view) {
        this.view = view;
        this.model = new WeatherModel();
    }

    @Override
    public void onFinishedSuccess(WeatherResponse weatherResponse) {
        if (view != null){
            view.hideProgress();
        }
        view.actionOnSuccess(weatherResponse);
    }

    @Override
    public void onFinishedFail(GenericErrorResponseBean genericErrorResponseBean) {
        if (view != null) {
            view.hideProgress();
        }
        view.showDialogGagal(genericErrorResponseBean.getMessage());
    }

    @Override
    public void onFailure(Throwable t) {
        if (view != null) {
            view.hideProgress();
        }
        view.showDialogGagal(t.getMessage());
    }

    @Override
    public void onDestroy() {
        view = null;

    }

    @Override
    public void requestDataFromServer(String q, String appid) {
        if (view != null) {
            view.showProgress();
        }
        model.getWeather(this,q ,appid);
    }
}
