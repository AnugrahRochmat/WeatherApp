package anugrah.rochmat.weatherapp.data.mapper

import anugrah.rochmat.weatherapp.data.datasource.local.WeatherEntity
import anugrah.rochmat.weatherapp.data.model.ForecastResponse
import anugrah.rochmat.weatherapp.data.model.WeatherResponse
import anugrah.rochmat.weatherapp.domain.entity.Forecast
import anugrah.rochmat.weatherapp.domain.entity.ForecastItem
import anugrah.rochmat.weatherapp.domain.entity.Weather

object WeatherMapper {

    fun mapResponseToDomain(response: WeatherResponse): Weather {
        return Weather(
            id = response.id,
            cityName = response.name,
            country = response.sys.country,
            temperature = response.main.temp,
            feelsLike = response.main.feelsLike,
            humidity = response.main.humidity,
            pressure = response.main.pressure,
            windSpeed = response.wind.speed,
            description = response.weather.firstOrNull()?.description ?: "",
            icon = response.weather.firstOrNull()?.icon ?: ""
        )
    }

    fun mapDomainToEntity(weather: Weather): WeatherEntity {
        return WeatherEntity(
            id = weather.id,
            cityName = weather.cityName,
            country = weather.country,
            temperature = weather.temperature,
            feelsLike = weather.feelsLike,
            humidity = weather.humidity,
            pressure = weather.pressure,
            windSpeed = weather.windSpeed,
            description = weather.description,
            icon = weather.icon,
            timestamp = weather.timestamp
        )
    }

    fun mapEntityToDomain(entity: WeatherEntity): Weather {
        return Weather(
            id = entity.id,
            cityName = entity.cityName,
            country = entity.country,
            temperature = entity.temperature,
            feelsLike = entity.feelsLike,
            humidity = entity.humidity,
            pressure = entity.pressure,
            windSpeed = entity.windSpeed,
            description = entity.description,
            icon = entity.icon,
            timestamp = entity.timestamp
        )
    }

    fun mapForecastResponseToDomain(response: ForecastResponse): Forecast {
        val dailyForecasts = response.list
            .map { forecast ->
                ForecastItem(
                    dateUnix = forecast.dateUnix,
                    date = forecast.dtTxt,
                    temperature = forecast.main.temp,
                    minTemp = forecast.main.tempMin,
                    maxTemp = forecast.main.tempMax,
                    description = forecast.weather.firstOrNull()?.description ?: "",
                    icon = forecast.weather.firstOrNull()?.icon ?: "",
                    humidity = forecast.main.humidity
                )
            }

        return Forecast(
            cityName = response.city.name,
            forecasts = dailyForecasts
        )
    }
}