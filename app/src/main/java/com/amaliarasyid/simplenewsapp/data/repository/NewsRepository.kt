package com.amaliarasyid.simplenewsapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.amaliarasyid.simplenewsapp.data.entities.News
import com.amaliarasyid.simplenewsapp.data.entities.NewsWithSource
import com.amaliarasyid.simplenewsapp.data.entities.Source
import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliarasyid.simplenewsapp.data.room.dao.NewsDao
import com.amaliarasyid.simplenewsapp.data.source.NewsDataSource
import com.amaliarasyid.simplenewsapp.data.source.NewsRemoteDataSource
import com.amaliasrayid.storyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val dataSource: NewsRemoteDataSource,
    private val newsDao: NewsDao
    ): NewsDataSource {

    override fun getListTopHeadlines(apikey: String): Flow<Resource<List<ArticlesItem>>> = dataSource.getTopHeadlines(apikey)
    override fun getListBookmarkedNews(): Flow<List<NewsWithSource>> = newsDao.getNewsWithSource()

    override suspend fun insertNews(data: News) = newsDao.insertNews(data)

    override suspend fun insertSource(data: Source): Long = newsDao.insertSource(data)

    override suspend fun deleteNewsWithSource(newsId: Int, sourceId: Int) = newsDao.deleteNewsWithSource(newsId, sourceId)
    override fun findNews(publishedAt: String): NewsWithSource?= newsDao.findNews(publishedAt)

}