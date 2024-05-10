package com.example.appcentcasestudy.network


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.appcentcasestudy.model.DataClasses.NewsResponse

interface NewsService {
    @GET("v2/everything")
    fun getNews(@Query("q") query: String, @Query("apiKey") apiKey: String): Call<NewsResponse>
}
