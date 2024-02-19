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

            fetchTopBusinessNews()?.let { articles ->
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

    private val newsApiService: NewsApiService = RetrofitService.newsApiService

    suspend fun fetchTopBusinessNews(): List<Article>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = newsApiService.getTopBusinessNews().execute()
                if (response.isSuccessful) {
                    Log.d(TAG, response.body()?.articles.toString())
                    response.body()?.articles
                } else {
                    Log.d(TAG, "failed somewhere")
                    // Handle unsuccessful response
                    null
                }
            } catch (e: Exception) {
                Log.d(TAG, "encountered error: ", e)
                // Handle network errors
                null
            }
        }
    }
}
