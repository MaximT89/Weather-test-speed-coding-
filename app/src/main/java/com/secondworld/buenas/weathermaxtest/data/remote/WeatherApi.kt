package com.secondworld.buenas.weathermaxtest.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?units=metric&appid=86b46d0f343f828c91d9293c9c47d01d")
    suspend fun getTemperatureByCity(@Query("q") city : String) : Response<ResponseWeather>
}