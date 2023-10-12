package com.example.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.weatherapp.presentation.navigation.AppNavGraph
import com.example.weatherapp.presentation.screens.main.MainViewModel
import com.example.weatherapp.presentation.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

private const val PERMISSION_ACTIVITY_KEY = "PERMISSION_ACTIVITY_KEY"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavGraph()
                }
            }
        }
    }
}

