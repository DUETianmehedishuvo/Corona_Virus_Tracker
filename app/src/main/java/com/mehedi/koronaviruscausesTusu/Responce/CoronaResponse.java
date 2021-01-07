package com.mehedi.koronaviruscausesTusu.Responce;

import com.mehedi.koronaviruscausesTusu.Services.BangladeshDataService;
import com.mehedi.koronaviruscausesTusu.Services.DayByDayService;
import com.mehedi.koronaviruscausesTusu.Services.TotalDataServices;
import com.mehedi.koronaviruscausesTusu.Services.WorldAllDataServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CoronaResponse {

    @GET("all")
    Call<TotalDataServices> getTotalData();

    @GET("countries?sort=cases")
    Call<List<WorldAllDataServices>> getWorldAllDataServices();

    @GET("countries/Bangladesh")
    Call<BangladeshDataService> getBangladeshService();

    @GET()
    Call<BangladeshDataService> getCountryAdapter(@Url String urlString);

    @GET()
    Call<List<DayByDayService>> getDaybyDayServices(@Url String urlString);
}
