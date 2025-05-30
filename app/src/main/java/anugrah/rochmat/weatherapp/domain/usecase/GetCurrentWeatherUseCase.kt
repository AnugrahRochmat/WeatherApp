package anugrah.rochmat.weatherapp.domain.usecase

import anugrah.rochmat.weatherapp.domain.entity.Weather
import anugrah.rochmat.weatherapp.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Observable

class GetCurrentWeatherUseCase(private val repository: WeatherRepository) {
    operator fun invoke(cityName: String): Observable<Weather> {
        return repository.getCurrentWeather(cityName)
    }
}