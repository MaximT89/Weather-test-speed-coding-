package com.secondworld.buenas.weathermaxtest.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite")
data class CityFavouriteEntity(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "city") val city : String,
    @ColumnInfo(name = "temperature") val temperature: String
)