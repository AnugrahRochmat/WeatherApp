package anugrah.rochmat.weatherapp.data.datasource.remote

import anugrah.rochmat.weatherapp.data.model.ForecastResponse
import anugrah.rochmat.weatherapp.data.model.WeatherResponse
import anugrah.rochmat.weather.BuildConfig
import io.reactivex.rxjava3.core.Observable

interface WeatherRemoteDataSource {
    fun getCurrentWeather(cityName: String): Observable<WeatherResponse>
    fun getCurrentWeatherByCoordinate(lat: Double, lon: Double): Observable<WeatherResponse>
    fun getForecast(cityName: String): Observable<ForecastResponse>
}

class WeatherRemoteDataSourceImpl(
    private val apiService: WeatherApiService
) : WeatherRemoteDataSource {

    override fun getCurrentWeather(cityName: String): Observable<WeatherResponse> {
        return apiService.getCurrentWeather(cityName, BuildConfig.API_KEY)
    }

    override fun getCurrentWeatherByCoordinate(lat: Double, lon: Double): Observable<WeatherResponse> {
        return apiService.getCurrentWeatherByCoordinate(lat, lon, BuildConfig.API_KEY)
    }

    override fun getForecast(cityName: String): Observable<ForecastResponse> {
        return apiService.getForecast(cityName, BuildConfig.API_KEY)
    }
}