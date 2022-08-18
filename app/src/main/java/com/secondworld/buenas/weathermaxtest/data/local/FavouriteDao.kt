package com.secondworld.buenas.weathermaxtest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface FavouriteDao {

    @Insert(entity = CityFavouriteEntity::class, onConflict = REPLACE)
    suspend fun save(city : CityFavouriteEntity)

    @Query("SELECT * FROM favourite")
    suspend fun getAllCities() : List<CityFavouriteEntity>

    @Query("DELETE FROM favourite WHERE city = :city")
    suspend fun deleteCity(city : String)
}