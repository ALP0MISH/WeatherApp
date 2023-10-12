package com.example.weatherapp.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.use_cases.FetchWeathersUseCase
import com.example.weatherapp.presentation.models.CountryInfo
import com.example.weatherapp.presentation.models.WeatherUI
import com.example.weatherapp.presentation.utils.mappers.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MainScreenUiState {
    object Loading : MainScreenUiState()

    data class Loaded(
        val weather: WeatherUI,
        val countryInfo: CountryInfo
    ) : MainScreenUiState()

    data class Error(
        val message: String
    ) : MainScreenUiState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchWeathersUseCase: FetchWeathersUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    fun fetchCurrentWeather(){
        _uiState.tryEmit(MainScreenUiState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val weatherParams = fetchWeathersUseCase.invoke()
            val weather = weatherParams.first.toUi()
            if (weather.isUnknown()) {
                _uiState.tryEmit(MainScreenUiState.Error("Что-то пошло не так"))
            } else {
                _uiState.tryEmit(
                    MainScreenUiState.Loaded(weather,weatherParams.second))
            }
        }
    }
}