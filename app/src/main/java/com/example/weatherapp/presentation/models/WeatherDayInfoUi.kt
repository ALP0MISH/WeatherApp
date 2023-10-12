package com.example.weatherapp.presentation.models

import java.util.Date

data class WeatherHourInfoUi(
    val temperature: Double,
    val weatherCondition: WeatherCondition,
    val date: Date,
    val windSpeed: Double,
){
    companion object {
        val unknown = WeatherHourInfoUi(
            temperature = 0.0,
            weatherCondition = WeatherCondition.UNKNOWN,
            windSpeed = 0.0,
            date = Date()
        )
    }
}

data class WeatherDayInfoUi(
    val temperature: Double,
    val weatherCondition: WeatherCondition,
    val time: String,
    val windSpeed: Double,
    val hourlyWeathers: List<WeatherHourInfoUi>
) {
    companion object {
        val unknown = WeatherDayInfoUi(
            temperature = 0.0,
            weatherCondition = WeatherCondition.UNKNOWN,
            windSpeed = 0.0,
            time = "unknown",
            hourlyWeathers = emptyList()
        )
    }
}

