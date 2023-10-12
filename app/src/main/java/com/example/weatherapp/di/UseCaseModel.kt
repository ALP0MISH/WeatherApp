package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.domain.managers.LocationTrackerManager
import com.example.weatherapp.domain.use_cases.FetchWeathersUseCase
import com.example.weatherapp.domain.use_cases.FetchWeathersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModel {
    @Provides
    fun provideFetchWeatherUseCase(
        @ApplicationContext context: Context,
        repository: WeatherRepository,
        locationTrackerManagerImpl: LocationTrackerManager
    ): FetchWeathersUseCase = FetchWeathersUseCaseImpl(
        repository = repository,
        locationTrackerManager = locationTrackerManagerImpl,
        context = context
    )
}