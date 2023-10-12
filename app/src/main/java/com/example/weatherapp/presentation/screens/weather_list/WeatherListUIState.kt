package com.example.weatherapp.presentation.screens.weather_list

import com.example.weatherapp.presentation.models.CountryInfo
import com.example.weatherapp.presentation.models.WeatherDayInfoUi

sealed class WeatherListUIState {
    object Loading : WeatherListUIState()

    class Loaded(
        val dailyWeathers: List<WeatherDayInfoUi>,
        val countryInfo: CountryInfo
    ): WeatherListUIState()

    class Error(
        val message : String
    ): WeatherListUIState()
}