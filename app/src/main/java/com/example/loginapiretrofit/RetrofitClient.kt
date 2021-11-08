package com.example.loginapiretrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
//    private val client = OkHttpClient().newBuilder()
//        .addInterceptor(HttpLoggingInterceptor().apply {
//            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
//            else HttpLoggingInterceptor.Level.BODY
//        })
//        .connectTimeout(30, TimeUnit.SECONDS)
//        .writeTimeout(30, TimeUnit.SECONDS)
//        .readTimeout(30, TimeUnit.SECONDS)
//        .build()

    val webService: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiInterface::class.java)
    }
}

