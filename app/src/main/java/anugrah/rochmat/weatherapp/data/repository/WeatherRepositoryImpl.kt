package anugrah.rochmat.weatherapp.data.repository

import anugrah.rochmat.weatherapp.data.datasource.local.WeatherLocalDataSource
import anugrah.rochmat.weatherapp.data.datasource.remote.WeatherRemoteDataSource
import anugrah.rochmat.weatherapp.data.mapper.WeatherMapper
import anugrah.rochmat.weatherapp.domain.entity.Forecast
import anugrah.rochmat.weatherapp.domain.entity.Weather
import anugrah.rochmat.weatherapp.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource
) : WeatherRepository {

    override fun getCurrentWeather(cityName: String): Observable<Weather> {
        return remoteDataSource.getCurrentWeather(cityName)
            .map { WeatherMapper.mapResponseToDomain(it) }
            .doOnNext { weather ->
                localDataSource.saveWeather(WeatherMapper.mapDomainToEntity(weather))
                localDataSource.deleteOldWeather()
            }
            .onErrorResumeNext {
                localDataSource.getWeatherByCity(cityName)
                    .map { WeatherMapper.mapEntityToDomain(it) }
            }
            .subscribeOn(Schedulers.io())
    }

    override fun getCurrentWeatherByCoordinates(lat: Double, lon: Double): Observable<Weather> {
        return remoteDataSource.getCurrentWeatherByCoordinate(lat, lon)
            .map { WeatherMapper.mapResponseToDomain(it) }
            .doOnNext { weather ->
                localDataSource.saveWeather(WeatherMapper.mapDomainToEntity(weather))
                localDataSource.deleteOldWeather()
            }
            .subscribeOn(Schedulers.io())
    }

    override fun getForecast(cityName: String): Observable<Forecast> {
        return remoteDataSource.getForecast(cityName)
            .map { WeatherMapper.mapForecastResponseToDomain(it) }
            .subscribeOn(Schedulers.io())
    }

    override fun getCachedWeather(): Observable<List<Weather>> {
        return localDataSource.getAllWeather()
            .map { entities -> entities.map { WeatherMapper.mapEntityToDomain(it) } }
            .subscribeOn(Schedulers.io())
    }

    override fun searchCities(query: String): Observable<List<String>> {
        val cities = listOf(
            "London", "New York", "Tokyo", "Paris", "Sydney", "Berlin", "Moscow", "Dubai",
            "Singapore", "Hong Kong", "Los Angeles", "Chicago", "Toronto", "Mumbai", "Delhi",
            "Jakarta", "Bekasi"
        )
        return Observable.just(cities.filter { it.contains(query, ignoreCase = true) })
    }
}