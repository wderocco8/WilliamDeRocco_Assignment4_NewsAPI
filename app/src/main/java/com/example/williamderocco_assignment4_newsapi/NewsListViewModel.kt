package com.example.williamderocco_assignment4_newsapi

import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.UUID
import kotlin.random.Random



class NewsListViewModel : ViewModel() {

    val newsList = mutableListOf<News>()

    init {
        for (i in 0 until 100) {
            val news = News(
                id = UUID.randomUUID(),
                title ="News #$i",
                date = Date(),
            )

            newsList += news
        }
    }
}
