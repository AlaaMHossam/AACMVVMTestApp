package com.lifetimecode.aacmvvmtestapp.data.dagger.modules

import com.google.gson.GsonBuilder
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.Webservice
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    internal fun webservice(retrofit: Retrofit): Webservice {
        return retrofit.create<Webservice>(Webservice::class.java)
    }

    @Provides
    internal fun retrofit(): Retrofit {
        val gson = GsonBuilder()
            .create()

        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .followSslRedirects(true)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://www.weelo.com.eg/api/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}