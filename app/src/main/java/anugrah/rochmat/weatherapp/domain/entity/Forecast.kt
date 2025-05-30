package anugrah.rochmat.weatherapp.domain.entity

class Forecast(
    val cityName: String,
    val forecasts: List<ForecastItem>
)

class ForecastItem(
    val dateUnix: Long,
    val date: String,
    val temperature: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val description: String,
    val icon: String,
    val humidity: Int
)