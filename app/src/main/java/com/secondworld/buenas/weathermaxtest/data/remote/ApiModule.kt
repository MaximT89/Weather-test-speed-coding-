package com.secondworld.buenas.weathermaxtest.data.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideWeatherApi(retrofit: Retrofit) : WeatherApi = retrofit.create(WeatherApi::class.java)
}