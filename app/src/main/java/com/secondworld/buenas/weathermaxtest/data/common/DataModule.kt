package com.secondworld.buenas.weathermaxtest.data.common

import com.secondworld.buenas.weathermaxtest.data.WeatherRepositoryImpl
import com.secondworld.buenas.weathermaxtest.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindRepository(repository: WeatherRepositoryImpl) : WeatherRepository
}