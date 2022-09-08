package com.secondworld.buenas.weathermaxtest.ui.screens.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secondworld.buenas.weathermaxtest.core.extension.log
import com.secondworld.buenas.weathermaxtest.domain.entity.CityFavouriteDomain
import com.secondworld.buenas.weathermaxtest.domain.interactor.WeatherInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val interactor: WeatherInteractor
) : ViewModel() {

    private var _listCities = MutableLiveData<List<CityFavouriteDomain>?>()
    val listCities: LiveData<List<CityFavouriteDomain>?> = _listCities

    init {
        fetchCitiesFromBd()
    }

    fun fetchCitiesFromBd() {
        viewModelScope.launch {
            getAllCities()
        }
    }

    fun deleteCity(city: String) {
        viewModelScope.launch {
            delCityBd(city)
            getAllCities()
        }
    }

    private suspend fun getAllCities() = withContext(Dispatchers.IO) {
        val list = interactor.getAllCitiesFromFavourite()
        _listCities.postValue(list)

        withContext(Dispatchers.Main) {
            _listCities.value.let { list ->
                _listCities.value = list?.map {
                    it.copy()
                }
            }
        }
    }

    private suspend fun delCityBd(city: String) = withContext(Dispatchers.IO){
        interactor.deleteCityFromFavourite(city)
    }
}