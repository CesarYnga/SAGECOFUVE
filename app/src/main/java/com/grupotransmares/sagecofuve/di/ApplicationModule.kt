package com.grupotransmares.sagecofuve.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.grupotransmares.sagecofuve.data.network.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class ApplicationModule {

    @Provides
    @Singleton internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesApiService(gson: Gson): ApiService {
        val logging = HttpLoggingInterceptor({ message ->
            Timber.tag("OkHttp").d(message)
        })
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(15, TimeUnit.SECONDS)
        httpClient.readTimeout(15, TimeUnit.SECONDS)
        httpClient.writeTimeout(15, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)
        val retrofit = Retrofit.Builder().baseUrl(ApiService.SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
        return retrofit.create(ApiService::class.java)
    }
}