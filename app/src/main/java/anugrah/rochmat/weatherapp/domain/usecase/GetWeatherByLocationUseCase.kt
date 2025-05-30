package anugrah.rochmat.weatherapp.domain.usecase

import anugrah.rochmat.weatherapp.domain.entity.Weather
import anugrah.rochmat.weatherapp.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Observable

class GetWeatherByLocationUseCase(private val repository: WeatherRepository) {
    operator fun invoke(lat: Double, lon: Double): Observable<Weather> {
        return repository.getCurrentWeatherByCoordinates(lat, lon)
    }
}