package com.radea.alquranku.core.data.di

import androidx.room.Room
import com.radea.alquranku.BuildConfig
import com.radea.alquranku.core.data.ScheduleSholatRepository
import com.radea.alquranku.core.data.SurahRepository
import com.radea.alquranku.core.data.source.local.LocalDataSource
import com.radea.alquranku.core.data.source.local.room.AlquranDatabase
import com.radea.alquranku.core.data.source.remote.RemoteDataSource
import com.radea.alquranku.core.data.source.remote.network.ScheduleSholatApiService
import com.radea.alquranku.core.data.source.remote.network.SurahApiService
import com.radea.alquranku.core.domain.repository.IScheduleSholatRepository
import com.radea.alquranku.core.domain.repository.ISurahRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<AlquranDatabase>().alquranDao() }
    single {
        Room.databaseBuilder(
            androidContext(), AlquranDatabase::class.java, "Alquran.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        provideDefaultOkHttpClient()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT_QURAN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(SurahApiService::class.java)
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT_SCHEDULE_SHOLAT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ScheduleSholatApiService::class.java)
    }
}

val repositoryModule = module {
    single {
        LocalDataSource(get())
    }
    single {
        RemoteDataSource(get(), get())
    }
    single<ISurahRepository> { SurahRepository(get(), get()) }
    single<IScheduleSholatRepository> { ScheduleSholatRepository(get(), get()) }
}


private fun provideDefaultOkHttpClient(): OkHttpClient {
    val loggingIntercept = HttpLoggingInterceptor()
    loggingIntercept.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClientBuilder = OkHttpClient.Builder()
    return if (BuildConfig.DEBUG) {
        okHttpClientBuilder
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    } else {
        okHttpClientBuilder
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}