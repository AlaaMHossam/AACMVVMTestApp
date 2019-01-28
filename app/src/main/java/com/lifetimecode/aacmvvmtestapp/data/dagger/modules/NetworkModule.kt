package com.lifetimecode.aacmvvmtestapp.data.dagger.modules

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lifetimecode.aacmvvmtestapp.App
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.ConnectivityInterceptor
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.Webservice
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule(private val app: App) {

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
            .addInterceptor(ConnectivityInterceptor(app))
            .followSslRedirects(true)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://emadmhossam.com/CAfeeds/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }
}