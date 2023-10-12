package com.example.weatherapp.domain

data class WeatherDomain(
    val currentWeather: WeatherDayInfoDomain,
    val otherWeathers: List<WeatherDayInfoDomain>
) {

    fun isUnknown() = this == unknown

    companion object {
        val unknown = WeatherDomain(
            currentWeather = WeatherDayInfoDomain.unknown,
            otherWeathers = listOf(WeatherDayInfoDomain.unknown)
        )
    }
}

