package com.secondworld.buenas.weathermaxtest.ui.screens.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secondworld.buenas.weathermaxtest.core.extension.log
import com.secondworld.buenas.weathermaxtest.domain.entity.CityFavouriteDomain
import com.secondworld.buenas.weathermaxtest.domain.interactor.WeatherInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope.launch(Dispatchers.IO) {
            getAllCities()
        }
    }

    fun deleteCity(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            delCityBd(city)
            getAllCities()
        }
    }

    suspend fun getAllCities() = withContext(Dispatchers.IO) {
        val list = interactor.getAllCitiesFromFavourite()
        log("getAllCities")
        _listCities.postValue(list)

        withContext(Dispatchers.Main) {
            _listCities.value.let { list ->
                _listCities.value = list?.map {
                    it.copy()
                }
            }
        }
    }

    suspend fun delCityBd(city: String) = withContext(Dispatchers.IO) {
        log("delCityBd")
        viewModelScope.launch(Dispatchers.IO) {
            interactor.deleteCityFromFavourite(city)
        }
    }
}