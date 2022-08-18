package com.secondworld.buenas.weathermaxtest.data.local

import com.secondworld.buenas.weathermaxtest.core.bases.Mapper
import com.secondworld.buenas.weathermaxtest.domain.entity.CityFavouriteDomain
import javax.inject.Inject

class CityDomainToDataMapper @Inject constructor() :
    Mapper<CityFavouriteDomain, CityFavouriteEntity> {
    override fun map(data: CityFavouriteDomain) =
        CityFavouriteEntity(0, data.city, data.temperature)
}