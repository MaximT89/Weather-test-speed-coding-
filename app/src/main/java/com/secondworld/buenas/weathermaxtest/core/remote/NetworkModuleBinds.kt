package com.secondworld.buenas.weathermaxtest.core.remote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModuleBinds {

    @Binds
    abstract fun bindResponseWrapper(responseWrapper: ResponseWrapper.Base) : ResponseWrapper
}