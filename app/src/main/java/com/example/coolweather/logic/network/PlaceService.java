package com.example.coolweather.logic.network;

import com.example.coolweather.logic.model.PlaceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceService {

    @GET("v2/place?token=${}&lang=zh_CN")
    Call<PlaceResponse> searchPlace(@Query("query") String query);

}
