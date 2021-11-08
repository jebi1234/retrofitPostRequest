package com.example.loginapiretrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

const val BASE_URL = "https://api.mrmoasilha.com"
interface ApiInterface {

    @POST(value = "v1/login/credentials")
    suspend fun login(@Body request: LoginRequest, @Header("Authorization") token: String): Response<LoginResponse>?

}