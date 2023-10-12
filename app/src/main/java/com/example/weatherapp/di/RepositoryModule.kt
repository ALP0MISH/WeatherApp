package com.example.weatherapp.di

import com.example.weatherapp.data.remote.WeatherService
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideWeatherRepository(
        weatherService: WeatherService
    ): WeatherRepository = WeatherRepositoryImpl(weatherService)
}