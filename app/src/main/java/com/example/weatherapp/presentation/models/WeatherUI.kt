package com.example.weatherapp.presentation.models

data class WeatherUI(
    val currentWeather: WeatherDayInfoUi,
    val otherWeathers: List<WeatherDayInfoUi>
) {

    fun isUnknown() = this == unknown

    companion object {
        val unknown = WeatherUI(
            currentWeather = WeatherDayInfoUi.unknown,
            otherWeathers = listOf(WeatherDayInfoUi.unknown)
        )
    }
}

