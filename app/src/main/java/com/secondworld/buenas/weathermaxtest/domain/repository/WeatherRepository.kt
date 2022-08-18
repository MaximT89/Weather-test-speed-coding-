package com.secondworld.buenas.weathermaxtest.domain.repository

import com.secondworld.buenas.weathermaxtest.core.bases.BaseResult
import com.secondworld.buenas.weathermaxtest.core.remote.Failure
import com.secondworld.buenas.weathermaxtest.domain.entity.CityFavouriteDomain
import com.secondworld.buenas.weathermaxtest.domain.entity.WeatherDomain

interface WeatherRepository {

    suspend fun getWeatherByCity(city: String) : BaseResult<WeatherDomain, Failure>

    suspend fun saveInFavourite(city: CityFavouriteDomain)

    suspend fun deleteCityFromFavourite(city : String)

    suspend fun getAllCitiesFromFavourite() : List<CityFavouriteDomain>
}