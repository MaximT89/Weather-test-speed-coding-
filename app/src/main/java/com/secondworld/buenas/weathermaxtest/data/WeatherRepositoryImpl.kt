package com.secondworld.buenas.weathermaxtest.data

import com.secondworld.buenas.weathermaxtest.data.local.LocalDataSource
import com.secondworld.buenas.weathermaxtest.data.remote.CloudDataSource
import com.secondworld.buenas.weathermaxtest.domain.entity.CityFavouriteDomain
import com.secondworld.buenas.weathermaxtest.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val localDataSource: LocalDataSource
) : WeatherRepository {

    override suspend fun getWeatherByCity(city: String) = cloudDataSource.getWeatherByCity(city)

    override suspend fun saveInFavourite(city: CityFavouriteDomain) {
        localDataSource.save(city)
    }

    override suspend fun deleteCityFromFavourite(city: String) {
        localDataSource.deleteCity(city)
    }

    override suspend fun getAllCitiesFromFavourite() = localDataSource.loadAllCities()
}