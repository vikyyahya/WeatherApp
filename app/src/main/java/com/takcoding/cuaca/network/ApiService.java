package com.takcoding.cuaca.network;

import com.takcoding.cuaca.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("data/2.5/weather?")
    Call<WeatherResponse> getWeather(@Query("q") String q, @Query("appid") String appid);
}
