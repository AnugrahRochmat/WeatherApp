package anugrah.rochmat.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class WeatherResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "sys") val sys: Sys,
    @Json(name = "main") val main: Main,
    @Json(name = "weather") val weather: List<WeatherInfo>,
    @Json(name = "wind") val wind: Wind
)

@JsonClass(generateAdapter = true)
class Sys(
    @Json(name = "country") val country: String
)

@JsonClass(generateAdapter = true)
class Main(
    @Json(name = "temp") val temp: Double,
    @Json(name = "feels_like") val feelsLike: Double,
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "pressure") val pressure: Int,
    @Json(name = "temp_min") val tempMin: Double,
    @Json(name = "temp_max") val tempMax: Double
)

@JsonClass(generateAdapter = true)
class WeatherInfo(
    @Json(name = "main") val main: String,
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String
)

@JsonClass(generateAdapter = true)
class Wind(
    @Json(name = "speed") val speed: Double
)