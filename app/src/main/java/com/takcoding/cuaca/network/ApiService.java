package com.takcoding.cuaca.network;

import com.takcoding.cuaca.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiService {


    @GET("data/2.5/weather?q={}")
    Call<WeatherResponse> getWeather(@Path("city") String city, @Path("token") String token);
}
