package com.takcoding.cuaca.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.takcoding.cuaca.R;
import com.takcoding.cuaca.model.WeatherResponse;
import com.takcoding.cuaca.presenter.weather.WeatherContract;
import com.takcoding.cuaca.presenter.weather.WeatherModel;
import com.takcoding.cuaca.presenter.weather.WeatherPresenter;
import com.takcoding.cuaca.view.adapter.DetailWeatherInterface;
import com.takcoding.cuaca.view.adapter.WeatherListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements WeatherContract.View {

    private WeatherPresenter weatherPresenter;
    private String TOKEN = "2d7196be35cb7cf6dedf53a64f8a45ef";
    WeatherResponse [] wResponse  =  new WeatherResponse[3];
    private String[] city = {"jakarta", "surabaya", "palembang"};
    RecyclerView rv_weather ;
    WeatherListAdapter weatherListAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Cuaca Terkini");
        progressDialog = new ProgressDialog(this);
        rv_weather = findViewById(R.id.rv_weather);
        weatherPresenter = new WeatherPresenter(this);
        for (int i = 0 ; i < city.length ; i++){
            weatherPresenter.requestDataFromServer(city[i], "2d7196be35cb7cf6dedf53a64f8a45ef");
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void actionOnSuccess(WeatherResponse weatherResponse) {
        Log.d("actionOnSuccess", String.valueOf(wResponse.length));

        for(int i = 0 ; i < wResponse.length ; i++){
            if(wResponse[i] == null){
                wResponse[i] = weatherResponse;
                i = wResponse.length + 1;
            }
        }

        if(wResponse[wResponse.length - 1] != null){

            TextView temp, city , weather , description, feelslike, tempmin, temmax;

            weatherListAdapter = new WeatherListAdapter(wResponse, this, new DetailWeatherInterface() {
                @Override
                public void onClickDetil(WeatherResponse data, int position) {
                    Log.d("actionOnSuccess", String.valueOf(data.getMain().getFeelsLike()));
                    Intent intent = new Intent(MainActivity.this,DetailWeather.class);
                    intent.putExtra("city",String.valueOf(data.getName()));
                    intent.putExtra("temp",String.valueOf(data.getMain().getTemp()));
                    intent.putExtra("weather",String.valueOf(data.getWeather().get(0).getMain()));
                    intent.putExtra("description",String.valueOf(data.getWeather().get(0).getDescription()));

                    startActivity(intent);

                }
            });

            rv_weather.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            rv_weather.setVisibility(View.VISIBLE);
            rv_weather.setAdapter(weatherListAdapter);
            rv_weather.setOnFlingListener(null);
            SnapHelper snapHelper = new LinearSnapHelper();
            snapHelper.attachToRecyclerView(rv_weather);
        }


    }

    @Override
    public void showDialogGagal(String message) {
        progressDialog.hide();
    }

    @Override
    public void showProgress() {
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }
}