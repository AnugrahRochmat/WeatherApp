package anugrah.rochmat.weatherapp.data.datasource.remote

import anugrah.rochmat.weatherapp.data.model.ForecastResponse
import anugrah.rochmat.weatherapp.data.model.WeatherResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Observable<WeatherResponse>

    @GET("weather")
    fun getCurrentWeatherByCoordinate(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Observable<WeatherResponse>

    @GET("forecast")
    fun getForecast(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Observable<ForecastResponse>
}