package com.example.weatherapp.domain.use_cases

import com.example.weatherapp.domain.WeatherDomain
import com.example.weatherapp.presentation.models.CountryInfo

interface FetchWeathersUseCase {
    suspend operator fun invoke(): Pair< WeatherDomain, CountryInfo>
}