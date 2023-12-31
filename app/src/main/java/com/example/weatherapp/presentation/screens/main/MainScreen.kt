package com.example.weatherapp.presentation.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.R
import com.example.weatherapp.presentation.components.HourlyWeatherItemList
import com.example.weatherapp.presentation.models.CountryInfo
import com.example.weatherapp.presentation.models.WeatherDayInfoUi
import com.example.weatherapp.presentation.utils.DateType
import com.example.weatherapp.presentation.utils.fetchDayType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.time.Duration.Companion.hours

@Composable
fun LoadingScreen(
    uiStateFlow: StateFlow<MainScreenUiState>,
    navigateToWeatherList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState = uiStateFlow.collectAsStateWithLifecycle().value

    val fullScreenModifier = Modifier
        .fillMaxSize()

    if (uiState is MainScreenUiState.Error) {
        ErrorMainScreen(
            errorMessage = uiState.message,
            modifier = fullScreenModifier
        )
    }
    LoadingScreen(uiState = uiState, navigateToWeatherList = navigateToWeatherList)

}

@Composable
private fun LoadingScreen(
    uiState: MainScreenUiState,
    navigateToWeatherList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        val isLoaded = uiState is MainScreenUiState.Loaded
        val backGroundImageId = when (fetchDayType()) {
            DateType.SUNSET -> R.drawable.house_image_morning
            DateType.LIGHT -> R.drawable.house_image_night
            DateType.NIGHT -> R.drawable.house_image_afternoon
        }
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = backGroundImageId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        when (uiState) {
            is MainScreenUiState.Loading -> LoadingBlock()
            is MainScreenUiState.Loaded ->
                LoadedBlock(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    currentWeather = uiState.weather.currentWeather,
                    countryInfo = uiState.countryInfo
                )
            else -> Unit
        }
        Icon(
            modifier = Modifier
                .statusBarsPadding()
                .padding(end = 24.dp, top = 24.dp)
                .align(Alignment.TopEnd)
                .size(32.dp)
                .clickable { navigateToWeatherList() },
            painter = painterResource(id = R.drawable.menu_icon),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
fun BoxScope.LoadedBlock(
    currentWeather: WeatherDayInfoUi,
    countryInfo: CountryInfo,
    modifier: Modifier = Modifier
) {
    val currentHour = Date().hours
    var isShowInfoBlock by remember { mutableStateOf(false) }
    rememberCoroutineScope().launch {
        delay(100)
        isShowInfoBlock = true
    }
    AnimatedVisibility(
        modifier = Modifier.align(Alignment.TopCenter),
        visible = isShowInfoBlock,
        enter = fadeIn(
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = fadeOut(
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        WeatherInfo(
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 52.dp)
                .align(Alignment.TopCenter),
            temperature = "${currentWeather.temperature}°",
            windSpeed = "${currentWeather.windSpeed}Km/h",
            weatherType = currentWeather.weatherCondition.description,
            city = countryInfo.cityName
        )
    }
    AnimatedVisibility(
        modifier = Modifier.align(Alignment.BottomCenter),
        visible = isShowInfoBlock,
        enter = slideInVertically(
            animationSpec = tween(1000),
            initialOffsetY = { it / 2 }
        ),
        exit = slideOutVertically(
            animationSpec = tween(1000),
            targetOffsetY = { it / 2 }
        )
    ) {
        HourlyWeatherItemList(
            modifier = modifier,
            weatherHours = currentWeather.hourlyWeathers,
        )
    }
}

@Composable
fun BoxScope.LoadingBlock(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .statusBarsPadding()
            .padding(top = 52.dp)
            .fillMaxWidth()
            .height(300.dp)
            .align(Alignment.TopCenter),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMainScreen(
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun WeatherInfo(
    city: String,
    temperature: String,
    weatherType: String,
    windSpeed: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = city,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = temperature,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = 40.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = weatherType,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = windSpeed,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}