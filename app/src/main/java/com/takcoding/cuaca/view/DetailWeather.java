package com.takcoding.cuaca.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.takcoding.cuaca.R;

public class DetailWeather extends AppCompatActivity {
    TextView temp, city , weather , description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_weather);
        Intent myIntent = getIntent();
        temp = findViewById(R.id.tv_temp_det);
        city = findViewById(R.id.tv_city_det);
        weather = findViewById(R.id.tv_weather_det);
        description = findViewById(R.id.tv_description_det);


        temp.setText(myIntent.getStringExtra("temp"));
        city.setText(myIntent.getStringExtra("city"));
        weather.setText(myIntent.getStringExtra("weather"));
        description.setText(myIntent.getStringExtra("description"));


    }
}