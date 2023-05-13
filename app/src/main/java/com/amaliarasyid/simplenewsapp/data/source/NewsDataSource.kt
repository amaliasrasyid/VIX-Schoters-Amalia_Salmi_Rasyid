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
    fun getListBookmarkedNews(): LiveData<List<NewsWithSource>>
    fun insertNews(data: News)
    fun insertSource(data: Source): Long

    fun deleteNews(newsId: Int)
    fun deleteSource(sourceId: Int)

    fun deleteNewsWithSource(newsId: Int,sourceId: Int)
}