package com.example.williamderocco_assignment4_newsapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale.Category

interface NewsApiService {
    @GET("top-headlines?country=us&apiKey=9fd6f4f38e5d43b286e1378e0e283ec8")
    fun getTopNewsByCategory(@Query("category") category: String): Call<NewsResponse>
}
