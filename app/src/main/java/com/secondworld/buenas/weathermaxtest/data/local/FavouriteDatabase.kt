package com.secondworld.buenas.weathermaxtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [CityFavouriteEntity::class])
abstract class FavouriteDatabase : RoomDatabase(){

    abstract fun weatherDao() : FavouriteDao
}