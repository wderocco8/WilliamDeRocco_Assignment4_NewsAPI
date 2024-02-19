package com.example.williamderocco_assignment4_newsapi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "NewsListViewModel"

class NewsListViewModel : ViewModel() {

    val newsList = mutableListOf<News>()

    init {
        viewModelScope.launch {
            // Initialize newsApiService inside the coroutine
            val newsApiService = RetrofitService.newsApiService

            fetchTopBusinessNews(newsApiService)?.let { articles ->
                // Update the newsList with the fetched articles
                // For example, you can add them to the existing list
                articles.map { article ->
                    newsList += article
                }
            }

            Log.d(TAG, "newsList: " + newsList.toString())
        }
    }

    private suspend fun fetchTopBusinessNews(newsApiService: NewsApiService): List<News>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = newsApiService.getTopBusinessNews().execute()
                if (response.isSuccessful) {
                    response.body()?.articles
                } else {
                    // Handle unsuccessful response
                    null
                }
            } catch (e: Exception) {
                // Handle network errors
                Log.d(TAG, "encountered error: ", e)
                null
            }
        }
    }
}

