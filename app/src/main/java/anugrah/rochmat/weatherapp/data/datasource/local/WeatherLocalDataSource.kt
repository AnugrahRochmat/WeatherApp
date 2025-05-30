package anugrah.rochmat.weatherapp.data.datasource.local

import io.reactivex.rxjava3.core.Observable

interface WeatherLocalDataSource {
    fun getAllWeather(): Observable<List<WeatherEntity>>
    fun getWeatherByCity(cityName: String): Observable<WeatherEntity>
    fun saveWeather(weather: WeatherEntity)
    fun deleteOldWeather()
}

class WeatherLocalDataSourceImpl(
    private val weatherDao: WeatherDao
) : WeatherLocalDataSource {
    override fun getAllWeather(): Observable<List<WeatherEntity>> {
        return weatherDao.getAllWeather()
    }

    override fun getWeatherByCity(cityName: String): Observable<WeatherEntity> {
        return weatherDao.getWeatherByCity(cityName)
    }

    override fun saveWeather(weather: WeatherEntity) {
        return weatherDao.insertWeather(weather)
    }

    override fun deleteOldWeather() {
        val oneDayAgo = System.currentTimeMillis() - CACHE_DURATION
        weatherDao.deleteOldWeather(oneDayAgo)
    }

    companion object {
        private const val CACHE_DURATION = 24 * 60 * 60 * 1000L
    }
}