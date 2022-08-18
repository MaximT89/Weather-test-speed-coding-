package com.secondworld.buenas.weathermaxtest.data.remote

import com.secondworld.buenas.weathermaxtest.core.bases.Mapper
import com.secondworld.buenas.weathermaxtest.domain.entity.WeatherDomain
import javax.inject.Inject

class WeatherDataToDomainMapper @Inject constructor() : Mapper<ResponseWeather, WeatherDomain>  {
    override fun map(data: ResponseWeather) = WeatherDomain(data.name!!, data.main?.temp!!)
}