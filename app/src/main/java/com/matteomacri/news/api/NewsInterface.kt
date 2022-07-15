package com.matteomacri.news.api

import com.matteomacri.news.Constants
import com.matteomacri.news.models.News
import retrofit2.http.GET


interface NewsInterface {
    @GET("/v1/latest-news?language=it&apiKey=${Constants.API_KEY}")
    suspend fun getNewsIta(): News

    @GET("/v1/latest-news?language=en&apiKey=${Constants.API_KEY}")
    suspend fun getNewsEn(): News
}