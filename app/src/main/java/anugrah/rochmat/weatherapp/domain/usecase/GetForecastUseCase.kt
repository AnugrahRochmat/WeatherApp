package anugrah.rochmat.weatherapp.domain.usecase

import anugrah.rochmat.weatherapp.domain.entity.Forecast
import anugrah.rochmat.weatherapp.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Observable

class GetForecastUseCase(private val repository: WeatherRepository) {
    operator fun invoke(cityName: String): Observable<Forecast> {
        return repository.getForecast(cityName)
    }
}