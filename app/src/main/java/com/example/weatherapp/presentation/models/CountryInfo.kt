package com.example.weatherapp.presentation.models

data class CountryInfo(
    val cityName: String,
    val countryName: String,
) {
    fun fetchFullInfo() = "$countryName\n$cityName"

    companion object {
        val unknown = CountryInfo(
            cityName = "Unknown",
            countryName = "Unknown"
        )
    }
}
