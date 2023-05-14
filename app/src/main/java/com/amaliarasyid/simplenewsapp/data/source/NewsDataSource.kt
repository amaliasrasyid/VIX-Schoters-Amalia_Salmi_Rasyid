package com.amaliarasyid.simplenewsapp.data.source

import androidx.lifecycle.LiveData
import com.amaliarasyid.simplenewsapp.data.entities.News
import com.amaliarasyid.simplenewsapp.data.entities.NewsWithSource
import com.amaliarasyid.simplenewsapp.data.entities.Source
import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliasrayid.storyapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {
    fun getListTopHeadlines(apikey: String): Flow<Resource<List<ArticlesItem>>>
    fun getListBookmarkedNews(): Flow<List<NewsWithSource>>
    suspend fun insertNews(data: News)
    suspend fun insertSource(data: Source): Long
    suspend fun deleteNewsWithSource(newsId: Int,sourceId: Int)
    fun findNews(publishedAt: String): NewsWithSource?
}