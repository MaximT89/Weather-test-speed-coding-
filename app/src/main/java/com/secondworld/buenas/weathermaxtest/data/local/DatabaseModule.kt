package com.secondworld.buenas.weathermaxtest.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FavouriteDatabase =
        Room.databaseBuilder(
            context,
            FavouriteDatabase::class.java,
            "app_database"
        ).build()

    @Provides
    fun provideDao(appDatabase : FavouriteDatabase) : FavouriteDao = appDatabase.weatherDao()
}