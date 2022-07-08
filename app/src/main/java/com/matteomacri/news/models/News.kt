package com.matteomacri.news.models

data class News(
    val news: List<New>,
    val page: Int,
    val status: String
)