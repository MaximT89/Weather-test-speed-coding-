package com.secondworld.buenas.weathermaxtest.data.local

import com.secondworld.buenas.weathermaxtest.core.bases.Mapper
import com.secondworld.buenas.weathermaxtest.domain.entity.CityFavouriteDomain
import javax.inject.Inject

class CityDataToDomainMapper @Inject constructor() :
    Mapper<CityFavouriteEntity, CityFavouriteDomain> {
    override fun map(data: CityFavouriteEntity) = CityFavouriteDomain(data.city, data.temperature)
}