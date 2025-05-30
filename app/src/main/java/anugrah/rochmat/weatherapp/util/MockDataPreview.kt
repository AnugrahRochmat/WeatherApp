package anugrah.rochmat.weatherapp.util

import anugrah.rochmat.weatherapp.domain.entity.Forecast
import anugrah.rochmat.weatherapp.domain.entity.ForecastItem
import anugrah.rochmat.weatherapp.domain.entity.Weather

object MockDataPreview {
    val mockWeather = Weather(
        id = 1,
        cityName = "Bekasi",
        country = "ID",
        temperature = 15.5,
        feelsLike = 17.0,
        humidity = 65,
        pressure = 1013,
        windSpeed = 3.2,
        description = "Partly Cloudy",
        icon = "10d"
    )

    val mockForecastItem = ForecastItem(
        dateUnix = 1748520000,
        date = "1748520000",
        temperature = 15.5,
        minTemp = 30.79,
        maxTemp = 31.02,
        description = "Scattered Clouds",
        icon = "10n",
        humidity = 70
    )

    private val mockForecastItems = listOf(
        ForecastItem(
            dateUnix = 1748520000,
            date = "1748520000",
            temperature = 26.0,
            minTemp = 21.0,
            maxTemp = 29.0,
            description = "Scattered Clouds",
            icon = "09d",
            humidity = 85
        ),
        ForecastItem(
            dateUnix = 1748520000,
            date = "1748520000",
            temperature = 28.0,
            minTemp = 23.0,
            maxTemp = 31.0,
            description = "Scattered Clouds",
            icon = "11d",
            humidity = 80
        ),
        ForecastItem(
            dateUnix = 1748520000,
            date = "1748520000",
            temperature = 30.0,
            minTemp = 25.0,
            maxTemp = 33.0,
            description = "Scattered Clouds",
            icon = "01d",
            humidity = 65
        ),
        ForecastItem(
            dateUnix = 1748520000,
            date = "1748520000",
            temperature = 30.0,
            minTemp = 25.0,
            maxTemp = 33.0,
            description = "Scattered Clouds",
            icon = "01d",
            humidity = 65
        ),
        ForecastItem(
            dateUnix = 1748520000,
            date = "1748520000",
            temperature = 30.0,
            minTemp = 25.0,
            maxTemp = 33.0,
            description = "Scattered Clouds",
            icon = "01d",
            humidity = 65
        )
    )

    val mockForecast = Forecast(
        cityName = "Bekasi",
        forecasts = mockForecastItems
    )
}