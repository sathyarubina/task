package com.mvvm.task.network

import retrofit2.Call
import retrofit2.http.*

interface RetroServiceInstance {

    //https://gorest.co.in/public-api/users
    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json")
    suspend fun getVideoList(@Query("page") page: Int): VideoList



}