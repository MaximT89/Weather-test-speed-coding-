package com.secondworld.buenas.weathermaxtest.ui.screens.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secondworld.buenas.weathermaxtest.core.bases.BaseResult
import com.secondworld.buenas.weathermaxtest.domain.entity.CityFavouriteDomain
import com.secondworld.buenas.weathermaxtest.domain.entity.WeatherDomain
import com.secondworld.buenas.weathermaxtest.domain.interactor.WeatherInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor : WeatherInteractor
): ViewModel() {

    private var _weatherState = MutableLiveData<WeatherState>()
    val weatherState : LiveData<WeatherState> = _weatherState

    fun getTemperature(city: String) {
        viewModelScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                _weatherState.value = WeatherState.Loading
            }
            when(val result = interactor.getTemperatureByCity(city)){
                is BaseResult.Error -> {
                    if(result.err.code != 0){
                        _weatherState.postValue(WeatherState.Error("city not found"))
                    } else {
                        _weatherState.postValue(WeatherState.NoInternet("Отсутствует интернет"))
                    }
                }
                is BaseResult.Success -> {
                    _weatherState.postValue(WeatherState.Success(result.data))
                }
            }
        }
    }

    fun addFavourite(city: String, temperature: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveInFavourite(CityFavouriteDomain(city, temperature))
        }
    }
}

sealed class WeatherState{
    object Loading : WeatherState()
    class NoInternet(val message : String) : WeatherState()
    class Success(val data : WeatherDomain) : WeatherState()
    class Error(val message : String) : WeatherState()
}