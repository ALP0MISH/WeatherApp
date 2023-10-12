package com.example.weatherapp.domain

interface WeatherRepository {
    suspend fun fetchWeatherFor16Days(
        longitude: String,
        latitude: String
    ): WeatherDomain
}