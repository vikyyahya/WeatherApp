package com.takcoding.cuaca.presenter.weather;

import com.takcoding.cuaca.model.WeatherResponse;
import com.takcoding.cuaca.network.ApiBuilder;
import com.takcoding.cuaca.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherModel implements WeatherContract.Model {


    @Override
    public void getWeather(OnFinishedListener onFinishedListener, String q, String appid) {
        ApiService service = ApiBuilder.getClient().create(ApiService.class);

        Call<WeatherResponse> call = service.getWeather(q,appid);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                onFinishedListener.onFinishedSuccess(response.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }
}
