package com.example.williamderocco_assignment4_newsapi

import java.util.UUID
import java.util.Date

data class News(
    val id: UUID,
    val title: String,
    val date: Date,
)
