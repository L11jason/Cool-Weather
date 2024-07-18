package com.example.coolweather.logic.network;

import android.util.Log;

import com.example.coolweather.logic.model.DailyResponse;
import com.example.coolweather.logic.model.PlaceResponse;
import com.example.coolweather.logic.model.RealtimeResponse;

import java.io.IOException;

import retrofit2.Response;

public class CoolWeatherNetwork {
    private static PlaceService placeService = ServiceCreator.PCreate(PlaceService.class);
    private static PlaceResponse Presult = new PlaceResponse();

    public  PlaceResponse searchPlaces(String query) {

        //获取信息
        try {
            Response<PlaceResponse> response = placeService.searchPlace(query).execute();
            PlaceResponse body = response.body();
            if (body != null) {
                Presult = body;
                Log.d("SunnyWeatherNetWork", "response body is not null");
                Log.d("SunnyWeatherNetWork", "status is " + Presult.getStatus());
            } else {
                Log.d("SunnyWeatherNetWork", "response body is null");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("SunnyWeatherNetWork", "连接错误");
        } finally {
            return Presult;
        }
    }

    //下面是搜索天气模块
    private final WeatherService weatherService = ServiceCreator.WCreate(WeatherService.class);

    private static RealtimeResponse Rresult = new RealtimeResponse() ;
    private static DailyResponse Dresult = new DailyResponse();

    public  RealtimeResponse getRealtimeWeather(String lng,String lat) {


        try {
            Response<RealtimeResponse> response = weatherService.getRealtimeWeather(lng, lat).execute();
            if (response.body() != null) {
                Rresult = response.body();
                Log.d("SunnyWeatherNetWork1", "status is " + Rresult.getStatus());
                Log.d("SunnyWeatherNetWork1", "Realtime response body is not null");
            } else {
                Log.d("SunnyWeatherNetWork1", "Realtime response body is null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return Rresult;
        }
    }

    //获取未来天气数据
    public DailyResponse getDailyWeather(String lng,String lat) {
        Log.d("SunnyWeatherNetWork", "查看传入的lng和lat" + lng + " " + lat);

        try {
            Response<DailyResponse> response = weatherService.getDailyWeather(lng, lat).execute();
            if (response.body() != null) {
                Dresult = response.body();
                Log.d("SunnyWeatherNetWork2", "Dailytime response body is not null");
                Log.d("SunnyWeatherNetWork2", "Dresult is " + Dresult.getResult());
            } else {
                Log.d("SunnyWeatherNetWork2", "Daily response body is null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return Dresult;
        }
    }

}
