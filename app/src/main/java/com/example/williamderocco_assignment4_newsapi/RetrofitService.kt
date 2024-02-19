package com.example.williamderocco_assignment4_newsapi

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "RetrofitService"

object RetrofitService {
    private const val BASE_URL = "https://newsapi.org/v2/"

    private val retrofit: Retrofit by lazy {
        try {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        } catch (e: Exception) {
            // Log any errors during initialization
            Log.e(TAG, "Error initializing Retrofit: ${e.message}", e)
            throw e
        }
    }

    val newsApiService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}
