package com.secondworld.buenas.weathermaxtest.data.remote

import com.secondworld.buenas.weathermaxtest.core.remote.ResponseWrapper
import javax.inject.Inject

class CloudDataSource @Inject constructor(
    private val api : WeatherApi,
    private val responseHandler : ResponseWrapper,
    private val mapper : WeatherDataToDomainMapper
) {

    suspend fun getWeatherByCity(city : String) = responseHandler.handleResponse(mapper){
        api.getTemperatureByCity(city)
    }
}