package com.example.weatherapp.presentation.utils.mappers

import com.example.weatherapp.domain.WeatherDayInfoDomain
import com.example.weatherapp.domain.WeatherDomain
import com.example.weatherapp.domain.WeatherHourInfoDomain
import com.example.weatherapp.presentation.models.WeatherCondition
import com.example.weatherapp.presentation.models.WeatherDayInfoUi
import com.example.weatherapp.presentation.models.WeatherHourInfoUi
import com.example.weatherapp.presentation.models.WeatherUI

fun WeatherHourInfoDomain.toUi(): WeatherHourInfoUi {
    return this.run {
        WeatherHourInfoUi(
            temperature = temperature,
            weatherCondition = WeatherCondition.findWeatherTypeByCode(weatherCode),
            windSpeed = windSpeed,
            date = date
        )
    }
}

fun WeatherDayInfoDomain.toUi(): WeatherDayInfoUi {
    return if (this == WeatherDayInfoDomain.unknown)
        WeatherDayInfoUi.unknown
    else this.run {
        WeatherDayInfoUi(
            temperature = temperature,
            weatherCondition = WeatherCondition.findWeatherTypeByCode(weatherCode),
            windSpeed = windSpeed,
            time = time,
            hourlyWeathers = hourlyWeathers.map { it.toUi() }
        )
    }
}
fun WeatherDomain.toUi(): WeatherUI {
    return if (this.isUnknown()) WeatherUI.unknown
    else WeatherUI(
        currentWeather = currentWeather.toUi(),
        otherWeathers = otherWeathers.map { it.toUi() }
    )
}