package com.secondworld.buenas.weathermaxtest.core.bases

interface Mapper<T, R> {

    fun map(data : T) : R
}