package com.secondworld.buenas.weathermaxtest.ui.screens.mainScreen

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import com.secondworld.buenas.weathermaxtest.core.bases.BaseFragment
import com.secondworld.buenas.weathermaxtest.core.extension.hide
import com.secondworld.buenas.weathermaxtest.core.extension.show
import com.secondworld.buenas.weathermaxtest.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("SetTextI18n")
@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(FragmentMainBinding::inflate) {
    override val viewModel: MainViewModel by viewModels()

    override fun initView(): Unit? = with(binding){
        btnSave.setOnClickListener { viewModel.addFavourite(city.text.toString() , temperature.text.toString()) }

        btnSearch.setOnClickListener { viewModel.getTemperature(editCity.text.toString()) }
    }


    override fun initObservers() = with(binding){
        viewModel.weatherState.observe(viewLifecycleOwner) {
            when(it){
                is WeatherState.Error -> {
                    dataField.hide()
                    errorField.show()
                    progressBar.hide()
                }
                WeatherState.Loading -> {
                    dataField.hide()
                    errorField.hide()
                    progressBar.show()
                }
                is WeatherState.NoInternet -> {
                    dataField.hide()
                    errorField.show()
                    progressBar.hide()
                }
                is WeatherState.Success -> {
                    dataField.show()
                    errorField.hide()
                    progressBar.hide()
                    city.text = it.data.city
                    temperature.text = "${it.data.temperature}°C"
                }
            }
        }
    }
}