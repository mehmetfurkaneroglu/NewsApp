package com.eroglu.newsapp.repository

import com.eroglu.newsapp.api.RetrofitInstance
import com.eroglu.newsapp.database.ArticleDatabase
import com.eroglu.newsapp.models.Article
import com.eroglu.newsapp.models.NewsResponse
import retrofit2.Response

class NewsRepository(val db: ArticleDatabase) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}