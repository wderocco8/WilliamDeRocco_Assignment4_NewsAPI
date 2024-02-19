package com.example.williamderocco_assignment4_newsapi

// ChatGPT -> used for SerializedName (not sure if needed)
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    val articles: List<News>
)

data class News(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    @SerializedName("urlToImage") val imageUrl: String?,
    @SerializedName("publishedAt") val publishDate: String,
    val content: String?
)

data class Source(
    val id: String?,
    val name: String
)
