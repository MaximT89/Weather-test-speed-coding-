package com.secondworld.buenas.weathermaxtest.domain.interactor

import com.secondworld.buenas.weathermaxtest.domain.entity.CityFavouriteDomain
import com.secondworld.buenas.weathermaxtest.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherInteractor @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend fun getTemperatureByCity(city: String) = repository.getWeatherByCity(city)

    suspend fun saveInFavourite(city: CityFavouriteDomain) {
        repository.saveInFavourite(city)
    }

    suspend fun deleteCityFromFavourite(city: String) {
        repository.deleteCityFromFavourite(city)
    }

    suspend fun getAllCitiesFromFavourite() = repository.getAllCitiesFromFavourite()
}