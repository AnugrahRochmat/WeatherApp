package anugrah.rochmat.weatherapp.util

import android.util.Log
import anugrah.rochmat.weatherapp.presentation.viewmodel.WeatherViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.java.KoinJavaComponent.get as koinGet  // ← Import with alias

object KoinUtils {
    fun safeLoadWeatherModules() {
//        try {
//            // Check if WeatherViewModel already exists
//            koinGet(WeatherViewModel::class.java)  // ← Use koinGet with class parameter
//            Log.d("KoinUtils", "Weather modules already loaded")
//        } catch (e: Exception) {
//            // WeatherViewModel not found, load modules
//            try {
//                Log.d("KoinUtils", "Loading weather modules...")
//                loadKoinModules(weatherModules)
//                Log.d("KoinUtils", "Weather modules loaded successfully")
//            } catch (loadException: Exception) {
//                Log.e("KoinUtils", "Failed to load weather modules", loadException)
//                loadException.printStackTrace()
//            }
//        }
    }
}