package com.takcoding.cuaca.view;

import retrofit2.http.GET;

public class DetailWeather {


    @GET("data/2.5/weather?q={}")
    Call<HomeResp> gridFeature(@Header("Authorization") String token,
                               @Path("userId") String userId);

}
