package com.example.weatherapp.presentation.models

import androidx.annotation.DrawableRes
import com.example.weatherapp.R

enum class WeatherCondition(
    val description: String,
    val weatherCode: List<Int>,
    @DrawableRes val lightImageID: Int,
    @DrawableRes val nightImageID: Int
) {
    CLEAR_SKY(
        weatherCode = listOf(0),
        description = "Sunset",
        lightImageID = R.drawable.claer_sky_light,
        nightImageID = R.drawable.clear_sky_night
    ),
    MAINLY_CLEAR(
        weatherCode = listOf(1),
        description = "Mainly clear",
        lightImageID = R.drawable.mainly_clear_light,
        nightImageID = R.drawable.mainly_clear_night
    ),
    PARTLY_CLOUDY(
        weatherCode = listOf(2),
        description = "Partly cloudy",
        lightImageID = R.drawable.partly_cloudy_light,
        nightImageID = R.drawable.partly_cloude_night
    ),
    OVERCAST(
        weatherCode = listOf(3),
        description = "OverCast",
        lightImageID = R.drawable.overcast_light,
        nightImageID = R.drawable.overcast_night
    ),
    FOG(
        weatherCode = listOf(45),
        description = "Fog",
        lightImageID = R.drawable.overcast_light,
        nightImageID = R.drawable.overcast_night
    ),
    DEPOSITION_FOG(
        weatherCode = listOf(48),
        description = "Deposition fog",
        lightImageID = R.drawable.overcast_light,
        nightImageID = R.drawable.overcast_night
    ),
    RAINY_SMALL(
        weatherCode = listOf(51, 56, 61),
        description = "Light rain",
        lightImageID = R.drawable.rain_small_light,
        nightImageID = R.drawable.rain_small_night
    ),
    RAINY_MEDIUM(
        weatherCode = listOf(53, 63),
        description = "Moderate rain",
        lightImageID = R.drawable.rain_madium_light,
        nightImageID = R.drawable.rain_medium_night
    ),
    RAINY_HIGHT(
        weatherCode = listOf(55, 57, 65),
        description = "Intensity rain",
        lightImageID = R.drawable.rain_big_ligth,
        nightImageID = R.drawable.rain_big_night
    ),
    FREEZING_RAIN_SMALL(
        weatherCode = listOf(66),
        description = "Light freezing rain",
        lightImageID = R.drawable.freezing_rain_small_ligth,
        nightImageID = R.drawable.freezing_rain_small_night
    ),
    FREEZING_RAIN_MEDIUM(
        weatherCode = listOf(67),
        description = "Moderate freezing rain",
        lightImageID = R.drawable.freezing_rain_medium,
        nightImageID = R.drawable.freezing_rain_medium
    ),
    SNOW_SMALL(
        weatherCode = listOf(71, 80, 85),
        description = "Light snow",
        lightImageID = R.drawable.lsnow_small,
        nightImageID = R.drawable.lsnow_small
    ),
    SNOW_MEDIUM(
        weatherCode = listOf(73, 81, 86),
        description = "Moderate snow",
        lightImageID = R.drawable.snow_medium_light,
        nightImageID = R.drawable.snow_medium_nigth
    ),
    SNOW_HIGHT(
        weatherCode = listOf(75, 77, 82),
        description = "Intensity snow",
        lightImageID = R.drawable.snow_hight,
        nightImageID = R.drawable.snow_hight
    ),
    STORM_SMALL(
        weatherCode = listOf(96),
        description = "Light storm",
        lightImageID = R.drawable.storm_small_light,
        nightImageID = R.drawable.storm_small_night
    ),
    STORM_MEDIUM(
        weatherCode = listOf(95, 99),
        description = "Light storm",
        lightImageID = R.drawable.storm_medium_light,
        nightImageID = R.drawable.storm_medium_night
    ),
    UNKNOWN(
        weatherCode = listOf(-1),
        description = "UNKNOWN",
        lightImageID = R.drawable.claer_sky_light,
        nightImageID = R.drawable.clear_sky_night
    );

    companion object {
        fun findWeatherTypeByCode(weatherCode: Int): WeatherCondition =
            values().find { weatherCondition ->
                weatherCondition.weatherCode.contains(weatherCode)
            } ?: UNKNOWN
    }

}
