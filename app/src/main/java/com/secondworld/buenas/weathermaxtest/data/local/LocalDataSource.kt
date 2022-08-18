package com.secondworld.buenas.weathermaxtest.data.local

import com.secondworld.buenas.weathermaxtest.domain.entity.CityFavouriteDomain
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao : FavouriteDao,
    private val mapperIn : CityDomainToDataMapper,
    private val mapperOut : CityDataToDomainMapper
) {

    suspend fun save(city : CityFavouriteDomain) {
        dao.save(mapperIn.map(city))
    }

    suspend fun loadAllCities() : List<CityFavouriteDomain> = dao.getAllCities().map{
        city -> mapperOut.map(city)
    }

    suspend fun deleteCity(city : String) {
        dao.deleteCity(city)
    }
}