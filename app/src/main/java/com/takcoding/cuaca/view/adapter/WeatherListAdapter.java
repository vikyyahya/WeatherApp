package com.takcoding.cuaca.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.takcoding.cuaca.R;
import com.takcoding.cuaca.model.WeatherResponse;
import com.takcoding.cuaca.presenter.weather.WeatherModel;
import com.takcoding.cuaca.view.DetailWeather;

import java.util.ArrayList;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.MyViewHolder>{

    WeatherResponse[] wResponse  =  new WeatherResponse[3];
    Activity context;
    DetailWeatherInterface detailWeatherInterface;


    public WeatherListAdapter(WeatherResponse[] wResponse, Activity context, DetailWeatherInterface detailWeatherInterface) {
        this.wResponse = wResponse;
        this.context = context;
        this.detailWeatherInterface = detailWeatherInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_city.setText(wResponse[position].getName());
        holder.tv_temp.setText(String.valueOf( wResponse[position].getMain().getTemp()));
        holder.cv_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailWeatherInterface.onClickDetil(wResponse[position],position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wResponse.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_city;
        TextView tv_temp;
        CardView cv_weather;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_city = itemView.findViewById(R.id.tv_city);
            tv_temp = itemView.findViewById(R.id.tv_temp);
            cv_weather = itemView.findViewById(R.id.cv_weather);

        }
    }
}
