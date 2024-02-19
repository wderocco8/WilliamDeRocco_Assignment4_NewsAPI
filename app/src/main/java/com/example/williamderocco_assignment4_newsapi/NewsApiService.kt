package com.example.williamderocco_assignment4_newsapi

import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {
    @GET("top-headlines?country=us&category=business&apiKey=9fd6f4f38e5d43b286e1378e0e283ec8")
    fun getTopBusinessNews(): Call<NewsResponse>
}
