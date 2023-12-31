package com.example.weatherapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.presentation.models.WeatherHourInfoUi
import com.example.weatherapp.presentation.theme.LightBlue
import com.example.weatherapp.presentation.theme.NightBlue
import com.example.weatherapp.presentation.utils.DateType
import com.example.weatherapp.presentation.utils.fetchDayType
import java.util.Date

@Preview
@Composable
fun HourlyWeatherItemPreview() {
    HourlyWeatherItemList(
        weatherHours = listOf(
            WeatherHourInfoUi.unknown,
            WeatherHourInfoUi.unknown,
            WeatherHourInfoUi.unknown,
            WeatherHourInfoUi.unknown,
            WeatherHourInfoUi.unknown,
        ),
    )
}

@Composable
fun HourlyWeatherItemList(
    weatherHours: List<WeatherHourInfoUi>,
    modifier: Modifier = Modifier
) {
    val currentHour = Date().hours

    val filteredWeathers = weatherHours.filter { it.date.hours >= currentHour }

    val sortedWeathers = filteredWeathers.sortedBy { it.date.hours }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp),
            painter = painterResource(id = R.drawable.hourly_weather_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(
                items = filteredWeathers
            ) { weather ->
                HourlyWeatherItem(
                    weatherHourInfoUi = weather,
                )
            }
        }
    }
}

@Composable
fun HourlyWeatherItem(
    weatherHourInfoUi: WeatherHourInfoUi,
    modifier: Modifier = Modifier
) {
    val isNow = weatherHourInfoUi.date.hours == Date().hours
    val actualColor = NightBlue

    val currentHour = Date().hours

    var isWeatherLight = false
    when (weatherHourInfoUi.date.hours) {
        in 19..23 -> isWeatherLight = false
        in 0..6 -> isWeatherLight = false
        in 7..18 -> isWeatherLight = true
    }
    Box(
        modifier = modifier
            .height(176.dp)
            .width(75.dp)
            .clip(RoundedCornerShape(30.dp))
            .border(
                1.dp, actualColor, RoundedCornerShape(20.dp)
            )
            .background(
                if (isNow) actualColor
                else actualColor.copy(alpha = 0.4f)
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = if (isNow) "Now"
                else "${weatherHourInfoUi.date.hours}h",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Image(
                modifier = modifier
                    .height(38.dp)
                    .width(44.dp),
                painter = if (isWeatherLight) painterResource(id = weatherHourInfoUi.weatherCondition.lightImageID)
                else painterResource(id = weatherHourInfoUi.weatherCondition.nightImageID),
                contentDescription = null
            )
            Text(
                text =  "${weatherHourInfoUi.temperature}°",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}