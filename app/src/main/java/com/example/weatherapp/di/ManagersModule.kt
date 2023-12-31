package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.domain.managers.LocationTrackerManager
import com.example.weatherapp.domain.managers.LocationTrackerManagerImpl
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ManagersModule {
    @Provides
    fun provideLocationTrackerManager(
        application: Application
    ): LocationTrackerManager = LocationTrackerManagerImpl(
        application = application,
        locationClient = LocationServices.getFusedLocationProviderClient(application)
    )
}