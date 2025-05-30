package anugrah.rochmat.weatherapp.domain.entity

class Weather(
    val id: Int,
    val cityName: String,
    val country: String,
    val temperature: Double,
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val description: String,
    val icon: String,
    val timestamp: Long = System.currentTimeMillis()
)