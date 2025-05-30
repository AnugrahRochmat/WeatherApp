package anugrah.rochmat.weatherapp.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import anugrah.rochmat.weatherapp.di.weatherModules
import anugrah.rochmat.weatherapp.presentation.ui.screen.WeatherScreen
import org.koin.core.context.loadKoinModules

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Weather.route
    ) {
        composable(NavigationRoutes.Weather.route) {
            var modulesLoaded by remember { mutableStateOf(false) }
            var error by remember { mutableStateOf<String?>(null) }

            LaunchedEffect(Unit) {
                try {
                    Log.d("Debug", "Loading weather modules...")
                    loadKoinModules(weatherModules)
                    modulesLoaded = true
                    Log.d("Debug", "Modules loaded successfully")
                } catch (e: Exception) {
                    Log.e("Debug", "Failed to load modules", e)
                    error = e.message
                }
            }

            when {
                error != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Error: $error")
                        Button(onClick = {
                            error = null
                            modulesLoaded = false
                        }) {
                            Text("Retry")
                        }
                    }
                }

                modulesLoaded -> {
                    WeatherScreen()
                }

                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

