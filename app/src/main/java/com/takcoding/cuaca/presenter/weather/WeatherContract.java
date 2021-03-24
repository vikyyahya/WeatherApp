package com.takcoding.cuaca.presenter.weather;

import com.takcoding.cuaca.model.WeatherResponse;
import com.takcoding.cuaca.util.GenericErrorResponseBean;

public interface WeatherContract {

    interface Model {
        void getWeather(OnFinishedListener onFinishedListener, String q, String appid);
        interface OnFinishedListener {
            void onFinishedSuccess(WeatherResponse weatherResponse);
            void onFinishedFail(GenericErrorResponseBean genericErrorResponseBean);
            void onFailure(Throwable t);
        }
    }

    interface View {
        void actionOnSuccess(WeatherResponse weatherResponse);
        void showDialogGagal(String message);
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void onDestroy();
        void requestDataFromServer(String q, String appid);
    }




}
