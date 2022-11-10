package com.example.avitoweatherapp.network

import com.example.avitoweatherapp.model.DataModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "7289898519b34208abe144638220611"
var CITY = ""
private const val DAYS = "7"
private const val ALERTS = "no"
private const val QUALITY_AIR = "no"

interface WeatherApi {
    @GET("./v1/forecast.json")
    fun getWeatherWeek(
        @Query("key")
        key:String = API_KEY,
        @Query("q")
        q:String = CITY,
        @Query("days")
        days: String = DAYS,
        @Query("aqi")
        aqi: String = QUALITY_AIR,
        @Query("alerts")
        alerts:String = ALERTS,
        @Query("lang")
        lang:String = "ru"
    ): Single<DataModel>
}