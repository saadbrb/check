package com.example.myapplication

import MyDataItem
import retrofit2.http.GET
import retrofit2.Call  // Der richtige Import für Retrofit Call

interface Api {

    @GET("posts")
    fun getData(): Call<List<MyDataItem>>

}
