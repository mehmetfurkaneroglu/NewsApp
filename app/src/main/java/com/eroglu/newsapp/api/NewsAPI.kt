package com.eroglu.newsapp.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.eroglu.newsapp.models.NewsResponse
import com.eroglu.newsapp.util.Constants.Companion.API_KEY
import retrofit2.Response

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        //countryCode: String = "tr",
        countryCode: String = "us",
        //ilk 20 haber için "page"
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

}