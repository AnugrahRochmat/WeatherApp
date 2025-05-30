package anugrah.rochmat.weatherapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather ORDER BY timestamp DESC")
    fun getAllWeather(): Observable<List<WeatherEntity>>

    @Query("SELECT * FROM weather WHERE cityName = :cityName")
    fun getWeatherByCity(cityName: String): Observable<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: WeatherEntity)

    @Query("DELETE FROM weather WHERE timestamp < :timestamp")
    fun deleteOldWeather(timestamp: Long)
}