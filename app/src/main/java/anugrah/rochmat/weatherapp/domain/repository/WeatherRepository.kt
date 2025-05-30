package anugrah.rochmat.weatherapp.domain.repository

import anugrah.rochmat.weatherapp.domain.entity.Forecast
import anugrah.rochmat.weatherapp.domain.entity.Weather
import io.reactivex.rxjava3.core.Observable

interface WeatherRepository {
    fun getCurrentWeather(cityName: String): Observable<Weather>
    fun getCurrentWeatherByCoordinates(lat: Double, lon: Double): Observable<Weather>
    fun getForecast(cityName: String): Observable<Forecast>
    fun getCachedWeather(): Observable<List<Weather>>
    fun searchCities(query: String): Observable<List<String>>
}