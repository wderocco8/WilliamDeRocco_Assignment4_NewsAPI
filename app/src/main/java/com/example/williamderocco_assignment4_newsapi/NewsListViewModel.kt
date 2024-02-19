package com.example.williamderocco_assignment4_newsapi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.UUID

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
                newsList.clear()
                newsList.addAll(articles.map { article ->
                    News(
                        id = UUID.randomUUID(),
                        title = article.title,
                        date = Date(),
                    )
                })
            }
        }
    }

    private suspend fun fetchTopBusinessNews(newsApiService: NewsApiService): List<Article>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = newsApiService.getTopBusinessNews().execute()
                if (response.isSuccessful) {
                    Log.d(TAG, response.body()?.articles.toString())
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

