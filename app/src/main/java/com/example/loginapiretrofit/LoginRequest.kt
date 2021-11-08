package com.example.loginapiretrofit

data class LoginRequest (
    val deviceType:String,
    val deviceId:String,
    val deviceVersion:String,
    val language:String,
    val appVersion:String,
    val iPAddress:String,
    val cabLatitude: String,
    val cabLongitude:String,
    val pushNotificationToken:String,
    val deviceMac:String,
    val deviceModel:String,
    val deviceName:String,
    val buildId:String,
    val customerType:String,
    val loginType:String

    )
