package com.example.loginapiretrofit

data class LoginResponse (

    val status:String,
    val type:String,
    val token:String,
    val timeStamp:String

    )
