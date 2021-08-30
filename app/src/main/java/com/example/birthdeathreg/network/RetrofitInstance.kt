package com.example.birthdeathreg.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api:ApiInterface by lazy{
        Retrofit.Builder()
            .baseUrl("http://117.240.239.43:8080/EMunicipalityAPI/emun/OBDRS/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}