package com.example.starcast.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkBuilder {

    fun <T> create(apiType: Class<T>): T =

        Retrofit.Builder()
            .baseUrl("https://swapi.dev/")
            .client(gmHttpClient())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiType)



    private fun gmHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY

            })

        builder.addInterceptor { chain ->
            var request = chain.request()
            val requestBuilder = request.newBuilder()
            request = requestBuilder.build()
            chain.proceed(request)
        }
        return builder.build()
    }



}
