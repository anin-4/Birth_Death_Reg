package com.example.birthdeathreg.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("getListValues")
    suspend fun postData(@Body postDataBody: PostDataBody):Response<DataClassHolder>
}