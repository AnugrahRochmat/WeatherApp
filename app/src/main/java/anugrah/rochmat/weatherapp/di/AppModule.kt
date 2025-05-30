package anugrah.rochmat.weatherapp.di

import androidx.room.Room
import anugrah.rochmat.weatherapp.data.datasource.local.WeatherDatabase
import anugrah.rochmat.weatherapp.data.datasource.remote.WeatherApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(NetworkConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    single<WeatherApiService> {
        get<Retrofit>().create(WeatherApiService::class.java)
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            WeatherDatabase::class.java,
            DatabaseConstants.WEATHER_DATABASE
        ).build()
    }

    single { get<WeatherDatabase>().weatherDao() }
}

val coreModules = listOf(
    networkModule,
    databaseModule
)
