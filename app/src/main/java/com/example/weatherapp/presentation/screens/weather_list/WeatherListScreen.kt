package com.example.weatherapp.presentation.screens.weather_list

import DailyWeatherItem
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.presentation.models.CountryInfo
import com.example.weatherapp.presentation.models.WeatherDayInfoUi
import com.example.weatherapp.presentation.theme.Purple
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun WeatherListScreenPreview() {
    MaterialTheme {
        WeatherListScreen(
            uiStateFlow = MutableStateFlow(WeatherListUIState.Loading)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherListScreen(
    uiStateFlow: StateFlow<WeatherListUIState>,
    modifier: Modifier = Modifier
) {
    val fullScreenModifier = Modifier
        .fillMaxSize()
        .background(Purple)
    val uiState = uiStateFlow.collectAsStateWithLifecycle().value
    when (uiState) {
        is WeatherListUIState.Loading -> {
            LoadingWeatherListScreen(fullScreenModifier)

        }

        is WeatherListUIState.Loaded -> {
            LoadedWeatherList(
                dailyWeathers = uiState.dailyWeathers,
                countryInfo = uiState.countryInfo,
                fullScreenModifier
            )
        }

        is WeatherListUIState.Error -> {
            ErrorWeatherListScreen(errorMessage = uiState.message, fullScreenModifier)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoadedWeatherList(
    dailyWeathers: List<WeatherDayInfoUi>,
    countryInfo: CountryInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.statusBarsPadding(),
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(vertical = 24.dp)

        ) {
            items(
                items = dailyWeathers,
                key = { item -> item.hashCode() }
            ) { weather ->
                DailyWeatherItem(weather = weather,countryInfo = countryInfo)
            }
        }
    }
}

@Composable
fun ErrorWeatherListScreen(
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun LoadingWeatherListScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) { CircularProgressIndicator() }
}
