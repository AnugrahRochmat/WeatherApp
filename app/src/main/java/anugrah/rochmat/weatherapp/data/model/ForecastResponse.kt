package anugrah.rochmat.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ForecastResponse(
    @Json(name = "city") val city: City,
    @Json(name = "list") val list: List<ForecastData>
)

@JsonClass(generateAdapter = true)
class City(
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
class ForecastData(
    @Json(name = "dt") val dateUnix: Long,
    @Json(name = "dt_txt") val dtTxt: String,
    @Json(name = "main") val main: Main,
    @Json(name = "weather") val weather: List<WeatherInfo>
)