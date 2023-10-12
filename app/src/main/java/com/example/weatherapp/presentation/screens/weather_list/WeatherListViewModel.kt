package com.example.weatherapp.presentation.screens.weather_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.use_cases.FetchWeathersUseCase
import com.example.weatherapp.presentation.utils.mappers.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val fetchWeatherUseCase: FetchWeathersUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<WeatherListUIState>(WeatherListUIState.Loading)
    val uiState: StateFlow<WeatherListUIState>  = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.tryEmit(WeatherListUIState.Loading)
            val weather = fetchWeatherUseCase.invoke()
            val dailyWeathers = weather.first.otherWeathers.map { it.toUi() }
            _uiState.tryEmit(WeatherListUIState.Loaded(dailyWeathers,weather.second))
        }
    }
}